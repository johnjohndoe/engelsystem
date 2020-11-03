package info.metadude.kotlin.library.engelsystem

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.engelsystem.models.Shift
import info.metadude.kotlin.library.engelsystem.utils.UserAgentInterceptor
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.fail
import org.junit.jupiter.api.Test
import org.threeten.bp.ZonedDateTime
import retrofit2.awaitResponse

class ProductionApiTest {

    companion object {
        const val BASE_URL = "https://staging.engelsystem.de"
        const val URL_PART_PATH = "test/shifts-json-export"
        const val VALID_API_KEY = "5dfc078751a0e382d0b96af0ae0fb3bb"
        const val INVALID_API_KEY = "invalid"
        val DEFAULT_DATE_TIME = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, Shift.DEFAULT_ZONE_OFFSET)!!
    }

    @Test
    fun `Validates a successful shifts response`() = runBlocking {
        try {
            val response = service.getShifts(URL_PART_PATH, VALID_API_KEY).awaitResponse()
            if (response.isSuccessful) {
                val shifts = response.body()
                assertThat(shifts).isNotNull()
                shifts?.forEach {
                    assertThat(it).isNotNull()
                    assertShift(it)
                }
            }
        } catch (t: Throwable) {
            fail("Should not throw $t")
        }
    }

    private fun assertShift(shift: Shift) {
        assertThat(shift.sID).isGreaterThan(0)
        assertThat(shift.typeId).isGreaterThan(0)
        assertThat(shift.name).isNotEmpty()
        assertThat(shift.locationName).isNotEmpty()
        assertThat(shift.locationDescription).isNotNull()
        assertThat(shift.locationUrl).isNotNull()
        assertThat(shift.talkTitle).isNotNull()
        assertThat(shift.talkUrl).isNotNull()
        assertThat(shift.userComment).isNotNull()
        assertThat(shift.startsAtInstant).isNotEqualTo(Shift.DEFAULT_INSTANT)
        assertThat(shift.endsAtInstant).isNotEqualTo(Shift.DEFAULT_INSTANT)
        assertThat(shift.startsAt).isNotEqualTo(DEFAULT_DATE_TIME)
        assertThat(shift.endsAt).isNotEqualTo(DEFAULT_DATE_TIME)
        assertThat(shift.timeZoneOffset).isNotNull()
    }

    @Test
    fun `Validates a failure shifts response`() = runBlocking {
        try {
            val response = service.getShifts(URL_PART_PATH, INVALID_API_KEY).awaitResponse()
            assertThat(response.isSuccessful).isFalse()
            assertThat(response.code()).isEqualTo(403)
            assertThat(response.body()).isNull()
            assertThat(response.errorBody()).isNotNull()
        } catch (t: Throwable) {
            fail("Should not throw $t")
        }
    }

    private val service: EngelsystemService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(UserAgentInterceptor("engelsystem-base library; ${javaClass.simpleName}"))
            .addNetworkInterceptor(interceptor)
            .build()
        ApiModule.provideEngelsystemService(BASE_URL, okHttpClient)
    }

}
