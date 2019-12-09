package info.metadude.kotlin.library.engelsystem.adapters

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.engelsystem.models.Shift
import org.junit.jupiter.api.Test
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime

class InstantAdapterTest {

    private val adapter = InstantAdapter()

    @Test
    fun `Converts 1565529594 to its instant representation`() {
        val actual = adapter.fromJson(1565529594) // 11.08.2019 13:19:54
        val expected = ZonedDateTime.of(2019, 8, 11, 13, 19, 54, 0, Shift.DEFAULT_ZONE_OFFSET).toInstant()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts 1565560800 to its instant representation`() {
        val actual = adapter.fromJson(1565560800) // 11.08.2019 22:00:00
        val expected = ZonedDateTime.of(2019, 8, 11, 22, 0, 0, 0, Shift.DEFAULT_ZONE_OFFSET).toInstant()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Converts 0 to null`() {
        val actual = adapter.fromJson(0)
        assertThat(actual).isEqualTo(Instant.EPOCH)
    }

    @Test
    fun `Converts null to null`() {
        val actual = adapter.fromJson(null)
        assertThat(actual).isEqualTo(null)
    }

}
