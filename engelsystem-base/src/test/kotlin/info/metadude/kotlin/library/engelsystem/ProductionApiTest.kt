package info.metadude.kotlin.library.engelsystem

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.engelsystem.models.Shift
import info.metadude.kotlin.library.engelsystem.utils.UserAgentInterceptor
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.threeten.bp.ZonedDateTime
import retrofit2.HttpException

class ProductionApiTest {

    companion object {
        const val BASE_URL = "https://staging.engelsystem.de"
        const val URL_PART_PATH = "test/shifts-json-export"
        const val VALID_API_KEY = "b8bb16fcd23500f2054480faad8df9db0884280fae8242182530e982cf76e87b"
        const val INVALID_API_KEY = "invalid"
        val DEFAULT_DATE_TIME = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, Shift.DEFAULT_ZONE_OFFSET)!!
    }

    @Test
    fun `Validates a successful shifts response`() = runTest {
        try {
            val shifts = service.getShifts(URL_PART_PATH, VALID_API_KEY)
            assertThat(shifts).isNotNull()
            shifts.forEach {
                assertThat(it).isNotNull()
                assertShift(it)
            }
        } catch (t: Throwable) {
            fail("Should not throw $t")
        }
    }

    private fun assertShift(shift: Shift) {
        assertThat(shift.sID).isGreaterThan(0)
        assertThat(shift.typeDescription).isNotNull()
        assertThat(shift.typeId).isGreaterThan(0)
        assertThat(shift.typeName).isNotEmpty()
        assertThat(shift.locationName).isNotEmpty()
        assertThat(shift.locationDescription).isNotNull()
        assertThat(shift.locationUrl).isNotNull()
        assertThat(shift.talkTitle).isNotNull()
        assertThat(shift.talkUrl).isNotNull()
        assertThat(shift.userComment).isNotNull()
        assertThat(shift.startsAtInstant).isNotEqualTo(Shift.DEFAULT_INSTANT)
        assertThat(shift.endsAtInstant).isNotEqualTo(Shift.DEFAULT_INSTANT)
        assertThat(shift.startsAt).isNotEqualTo(DEFAULT_DATE_TIME)
        assertThat(shift.startsAtDate).isNotEqualTo(DEFAULT_DATE_TIME)
        assertThat(shift.endsAt).isNotEqualTo(DEFAULT_DATE_TIME)
        assertThat(shift.endsAtDate).isNotEqualTo(DEFAULT_DATE_TIME)
        assertThat(shift.timeZoneName).isNotEmpty()
        assertThat(shift.timeZoneOffset).isNotNull()
    }

    @Test
    fun `Validates a failure shifts response`() = runTest {
        try {
            service.getShifts(URL_PART_PATH, INVALID_API_KEY)
            fail("Request should not succeed.")
        } catch (e: HttpException) {
            assertThat(e.message()).isEqualTo("Forbidden")
            assertThat(e.code()).isEqualTo(403)
            assertThat(e.response()!!.body()).isNull()
            assertThat(e.response()!!.errorBody()).isNotNull()
        }
    }

    private val service: EngelsystemService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(UserAgentInterceptor("engelsystem-base library; ${javaClass.simpleName}"))
            .addNetworkInterceptor(interceptor)
            .build()
        Api.provideEngelsystemService(BASE_URL, okHttpClient)
    }

}
