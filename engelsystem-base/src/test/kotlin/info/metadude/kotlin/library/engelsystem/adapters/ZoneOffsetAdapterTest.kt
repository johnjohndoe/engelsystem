package info.metadude.kotlin.library.engelsystem.adapters

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.ZoneOffset

class ZoneOffsetAdapterTest {

    private val adapter = ZoneOffsetAdapter()

    @Test
    fun `Converts +0100 to its zone offset representation`() {
        val actual = adapter.fromJson("+01:00")
        val expected = ZoneOffset.ofHours(1)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts empty string to null`() {
        val actual = adapter.fromJson("")
        assertThat(actual).isEqualTo(null)
    }

    @Test
    fun `Converts null to null`() {
        val actual = adapter.fromJson(null)
        assertThat(actual).isEqualTo(null)
    }

}
