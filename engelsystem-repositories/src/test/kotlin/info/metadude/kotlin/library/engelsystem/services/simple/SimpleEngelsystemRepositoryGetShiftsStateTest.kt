package info.metadude.kotlin.library.engelsystem.services.simple

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.engelsystem.EngelsystemApi
import info.metadude.kotlin.library.engelsystem.models.Shift
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState.Error
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState.Failure
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState.Success
import info.metadude.kotlin.library.engelsystem.repositories.simple.SimpleEngelsystemRepository
import info.metadude.kotlin.library.engelsystem.services.ImmediatelyFailingService
import info.metadude.kotlin.library.engelsystem.services.ImmediatelySucceedingService
import info.metadude.kotlin.library.engelsystem.services.ImmediatelyThrowingService
import kotlinx.coroutines.test.runTest
import okhttp3.Call
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class SimpleEngelsystemRepositoryGetShiftsStateTest {

    private lateinit var api: EngelsystemApi
    private val httpClient = mock<Call.Factory>()

    @Test
    fun `getShiftsState() returns success with list of shifts`(): Unit = runTest {
        val baseUrl = "https://example.com"
        api = mock {
            on { provideEngelsystemService(baseUrl, httpClient) }
                .doReturn(ImmediatelySucceedingService())
        }
        createRepository(api)
            .getShiftsState(
                requestETag = "",
                requestLastModifiedAt = "",
                baseUrl = baseUrl,
                path = "",
                apiKey = "",
            )
            .test {
                val actualState = awaitItem()
                assertThat(actualState).isInstanceOf(Success::class.java)
                val success = actualState as Success
                assertThat(success.shifts).isEqualTo(listOf(Shift()))
                assertThat(success.responseETag).isEqualTo("")
                assertThat(success.responseLastModifiedAt).isEqualTo("")
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState() returns failure with http error`(): Unit = runTest {
        val baseUrl = "https://example.com"
        api = mock {
            on { provideEngelsystemService(baseUrl, httpClient) }
                .doReturn(ImmediatelyFailingService())
        }
        createRepository(api)
            .getShiftsState(
                requestETag = "",
                requestLastModifiedAt = "",
                baseUrl = baseUrl,
                path = "",
                apiKey = "",
            )
            .test {
                val actualState = awaitItem()
                assertThat(actualState).isInstanceOf(Error::class.java)
                val error = actualState as Error
                assertThat(error.httpStatusCode).isEqualTo(503)
                assertThat(error.errorMessage).isEqualTo("Service Unavailable.")
                awaitComplete()
            }
    }

    @Test
    fun `getShiftsState() returns failure with runtime exception`(): Unit = runTest {
        val baseUrl = "https://example.com"
        api = mock {
            on { provideEngelsystemService(baseUrl, httpClient) }
                .doReturn(ImmediatelyThrowingService())
        }
        createRepository(api)
            .getShiftsState(
                requestETag = "",
                requestLastModifiedAt = "",
                baseUrl = baseUrl,
                path = "",
                apiKey = "",
            )
            .test {
                val actualState = awaitItem()
                assertThat(actualState).isInstanceOf(Failure::class.java)
                val failure = actualState as Failure
                assertThat(failure.throwable).isInstanceOf(RuntimeException::class.java)
                awaitComplete()
            }
    }

    private fun createRepository(api: EngelsystemApi) = SimpleEngelsystemRepository(
        callFactory = httpClient,
        api = api,
    )

}
