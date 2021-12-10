package info.metadude.kotlin.library.engelsystem.adapters

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeAdapterTest {

    private val adapter = ZonedDateTimeAdapter()

    @Test
    fun `Converts RFC3339 date to its ZonedDateTime representation`() {
        val actual = adapter.fromJson("2019-08-21T00:00:00+02:00")
        val expected = ZonedDateTime.of(2019, 8, 21, 0, 0, 0, 0, ZoneOffset.ofHours(2))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts date without zone offset to null`() {
        val actual = adapter.fromJson("2019-08-21T00:00:00")
        assertThat(actual).isEqualTo(null)
    }

    @Test
    fun `Converts 0 to null`() {
        val actual = adapter.fromJson("")
        assertThat(actual).isEqualTo(null)
    }

    @Test
    fun `Converts null to null`() {
        val actual = adapter.fromJson(null)
        assertThat(actual).isEqualTo(null)
    }

}
