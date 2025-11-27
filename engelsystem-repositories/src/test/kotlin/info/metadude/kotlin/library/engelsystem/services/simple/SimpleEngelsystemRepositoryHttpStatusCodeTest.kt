package info.metadude.kotlin.library.engelsystem.services.simple

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import info.metadude.kotlin.library.engelsystem.EngelsystemApi
import info.metadude.kotlin.library.engelsystem.EngelsystemService
import info.metadude.kotlin.library.engelsystem.adapters.ZonedDateTimeJsonAdapter
import info.metadude.kotlin.library.engelsystem.models.Shift
import info.metadude.kotlin.library.engelsystem.repositories.EngelsystemRepository
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState
import info.metadude.kotlin.library.engelsystem.repositories.simple.SimpleEngelsystemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import okhttp3.Call
import okhttp3.OkHttpClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.EOFException
import java.net.HttpURLConnection

class SimpleEngelsystemRepositoryHttpStatusCodeTest {

    private companion object {
        val STARTS_AT: ZonedDateTime = ZonedDateTime.of(2019, 8, 21, 13, 0, 0, 0, ZoneOffset.ofHours(2))
        val ENDS_AT: ZonedDateTime = STARTS_AT.plusHours(2).plusMinutes(45)
        const val VALID_ONE_ITEM_SHIFTS_JSON = """
                [
                    {
                        "user_comment": "This is a very secret comment.",
                        "Name": "Kirmes",
                        "RID": 12,
                        "SID": 579,
                        "TID": 10,
                        "UID": 32,
                        "created_at_timestamp": 1565979486,
                        "created_by_user_id": 32,
                        "description": "Kirmes are fun.",
                        "edited_at_timestamp": 1567367095,
                        "edited_by_user_id": 32,
                        "end_date": "2019-08-21T15:45:00+02:00",
                        "event_timezone": "Europe/Berlin",
                        "freeloaded": 0,
                        "id": 37,
                        "shifttype_id": 6,
                        "shifttype_name": "Name of the shift type",
                        "shifttype_description": "# Description of the shift type as markdown\n",
                        "start_date": "2019-08-21T13:00:00+02:00",
                        "title": "Tag 1: Decorate fridge"
                    }
                ]
                """
        const val INVALID_ONE_ITEM_SHIFTS_JSON = """
                {
                    "unknown": "foobar"
                }
                """
        const val EMPTY_ARRAY_SHIFTS_JSON = "[]"
        const val EMPTY_STRING = ""
        val EXPECTED_ONE_ITEM_SHIFTS = listOf(
            Shift(
                userComment = "This is a very secret comment.",
                endsAtDate = ENDS_AT,
                locationDescription = "Kirmes are fun.",
                locationName = "Kirmes",
                sID = 579,
                startsAtDate = STARTS_AT,
                talkTitle = "Tag 1: Decorate fridge",
                timeZoneName = "Europe/Berlin",
                typeId = 6,
                typeName = "Name of the shift type",
                typeDescription = "# Description of the shift type as markdown\n",
            )
        )

    }


    private val mockWebServer = MockWebServer()
    private val okHttpClient = mock<OkHttpClient>()
    private val repository = createRepository()

    @BeforeEach
    fun setUp() {
        mockWebServer.start()
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.close()
    }

    @Test
    fun `getShiftsState emits Failure when call responds with HTTP 200 and invalid JSON`() = runTest {
        repository
            .getShiftsStateMockResponse(
                responseHttpStatusCode = /* not relevant here */ HttpURLConnection.HTTP_OK,
                responseBody = INVALID_ONE_ITEM_SHIFTS_JSON,
            )
            .test {
                val shiftsResult = awaitItem()
                assertThat(shiftsResult).isInstanceOf(GetShiftsState.Failure::class.java)
                assertThat((shiftsResult as GetShiftsState.Failure).throwable).isInstanceOf(JsonDataException::class.java)
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState emits Failure when shifts JSON is empty`() = runTest {
        repository
            .getShiftsStateMockResponse(
                responseHttpStatusCode = /* not relevant here */ HttpURLConnection.HTTP_OK,
                responseBody = EMPTY_STRING,
            )
            .test {
                val shiftsResult = awaitItem()
                assertThat(shiftsResult).isInstanceOf(GetShiftsState.Failure::class.java)
                assertThat((shiftsResult as GetShiftsState.Failure).throwable).isInstanceOf(EOFException::class.java)
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState emits Success and empty list when call responds with HTTP 200 and empty array JSON`() =
        runTest {
            repository
                .getShiftsStateMockResponse(
                    responseHttpStatusCode = HttpURLConnection.HTTP_OK,
                    responseBody = EMPTY_ARRAY_SHIFTS_JSON,
                )
                .test {
                    val shiftsResult = awaitItem()
                    assertThat(shiftsResult).isEqualTo(
                        GetShiftsState.Success(
                            shifts = emptyList(),
                            responseETag = "",
                            responseLastModifiedAt = "",
                        )
                    )
                    awaitComplete()
                }
        }

    @Test
    fun `getShiftsState emits Success when call responds with HTTP 200`() = runTest {
        repository
            .getShiftsStateMockResponse(
                responseHttpStatusCode = HttpURLConnection.HTTP_OK,
                responseBody = VALID_ONE_ITEM_SHIFTS_JSON,
            )
            .test {
                val shiftsResult = awaitItem()
                assertThat(shiftsResult).isEqualTo(
                    GetShiftsState.Success(
                        shifts = EXPECTED_ONE_ITEM_SHIFTS,
                        responseETag = "",
                        responseLastModifiedAt = "",
                    )
                )
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState emits Error when call responds with HTTP 300`() = runTest {
        repository
            .getShiftsStateMockResponse(
                responseHttpStatusCode = HttpURLConnection.HTTP_MULT_CHOICE,
                responseBody = "Redirection",
            )
            .test {
                val shiftsResult = awaitItem()
                assertThat(shiftsResult).isEqualTo(
                    GetShiftsState.Error(
                        httpStatusCode = HttpURLConnection.HTTP_MULT_CHOICE,
                        errorMessage = "Redirection",
                    )
                )
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState emits Error when call responds with HTTP 400`() = runTest {
        repository
            .getShiftsStateMockResponse(
                responseHttpStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                responseBody = "Client Error",
            )
            .test {
                val shiftsResult = awaitItem()
                assertThat(shiftsResult).isEqualTo(
                    GetShiftsState.Error(
                        httpStatusCode = HttpURLConnection.HTTP_BAD_REQUEST,
                        errorMessage = "Client Error",
                    )
                )
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState emits Error when call responds with HTTP 500`() = runTest {
        repository
            .getShiftsStateMockResponse(
                responseHttpStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                responseBody = "Server Error",
            )
            .test {
                val shiftsResult = awaitItem()
                assertThat(shiftsResult).isEqualTo(
                    GetShiftsState.Error(
                        httpStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR,
                        errorMessage = "Server Error",
                    )
                )
                awaitComplete()
            }
    }

    /**
     * Performs an HTTP request against a [mockWebServer] using
     * the given [responseHttpStatusCode] and [responseBody] for the response.
     */
    private suspend fun EngelsystemRepository.getShiftsStateMockResponse(
        responseHttpStatusCode: Int,
        responseBody: String
    ): Flow<GetShiftsState> {
        val shiftsResponse = MockResponse.Builder()
            .addHeader("Content-Type", "application/json")
            .code(responseHttpStatusCode)
            .body(responseBody)
            .build()
        mockWebServer.enqueue(shiftsResponse)
        return getShiftsState(
            requestETag = "",
            requestLastModifiedAt = "",
            baseUrl = "",
            path = "",
            apiKey = "",
        )
    }

    private fun createRepository() = SimpleEngelsystemRepository(
        callFactory = okHttpClient,
        api = InterceptedServiceProvider(mockWebServer),
    )

    /**
     * Test-specific [EngelsystemApi] which injects the given [mockWebServer]
     * to return predefined responses.
     */
    private class InterceptedServiceProvider(private val mockWebServer: MockWebServer) : EngelsystemApi {

        override fun provideEngelsystemService(baseUrl: String, callFactory: Call.Factory): EngelsystemService {
            val moshi = Moshi.Builder()
                .add(ZonedDateTime::class.java, ZonedDateTimeJsonAdapter())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            return retrofit.create(EngelsystemService::class.java)
        }

    }

}
