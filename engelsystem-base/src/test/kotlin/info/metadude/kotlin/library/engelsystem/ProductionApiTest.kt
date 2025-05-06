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

class ProductionApiTest {

    companion object {
        const val BASE_URL = "https://staging.engelsystem.de"
        const val URL_PART_PATH = "test/shifts-json-export"
        const val VALID_API_KEY = "b8bb16fcd23500f2054480faad8df9db0884280fae8242182530e982cf76e87b"
        const val INVALID_API_KEY = "invalid"
        val DEFAULT_DATE_TIME = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, Shift.DEFAULT_ZONE_OFFSET)!!
    }

    @Test
    fun `getShifts responds successfully`() = runTest {
        try {
            val response = service.getShifts(
                eTag = "",
                lastModifiedAt = "",
                path = URL_PART_PATH,
                apiKey = VALID_API_KEY,
            )
            when (response.isSuccessful) {
                true -> {
                    val shifts = response.body()
                    assertThat(shifts).isNotNull()
                    shifts?.forEach {
                        assertThat(it).isNotNull()
                        assertShift(it)
                    }
                }

                false -> fail("Request failed with code ${response.code()}: ${response.message()}")
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
    fun `getShifts throws HTTP exception`() = runTest {
        try {
            val response = service.getShifts(
                eTag = "",
                lastModifiedAt = "",
                path = URL_PART_PATH,
                apiKey = INVALID_API_KEY,
            )
            when (response.isSuccessful) {
                false -> {
                    assertThat(response.code()).isEqualTo(403)
                    assertThat(response.message()).isEqualTo("Forbidden")
                    assertThat(response.body()).isNull()
                    assertThat(response.errorBody()).isNotNull()
                }

                true -> fail("Request should not succeed.")
            }
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
        Api.provideEngelsystemService(BASE_URL, okHttpClient)
    }

}
