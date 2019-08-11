package info.metadude.kotlin.library.engelsystem.adapters

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.engelsystem.ApiModule
import org.junit.jupiter.api.Test
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeAdapterTest {

    private val adapter = ZonedDateTimeAdapter()

    init {
        ApiModule.zoneOffset = ZoneOffset.UTC
    }

    @Test
    fun `Converts 1565529594 to its date time representation`() {
        val actual = adapter.fromJson(1565529594)
        val expected = ZonedDateTime.of(2019, 8, 11, 13, 19, 54, 0, ApiModule.zoneOffset)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts 1565560800 to its date time representation`() {
        val actual = adapter.fromJson(1565560800)
        val expected = ZonedDateTime.of(2019, 8, 11, 22, 0, 0, 0, ApiModule.zoneOffset)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts 0 to default the date time`() {
        val actual = adapter.fromJson(0)
        val expected = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ApiModule.zoneOffset)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts null to null`() {
        val actual = adapter.fromJson(null)
        assertThat(actual).isEqualTo(null)
    }

}
