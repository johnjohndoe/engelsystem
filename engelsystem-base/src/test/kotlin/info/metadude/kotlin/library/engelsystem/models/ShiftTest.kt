package info.metadude.kotlin.library.engelsystem.models

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

class ShiftTest {

    @Test
    fun `Assert a shift with minimal parameters can be constructed`() {
        val startsAt = ZonedDateTime.now()
        val endsAt = ZonedDateTime.now().plusHours(1)
        assertThat(
            Shift(
                userComment = "comment",
                endsAtDate = endsAt,
                locationDescription = "",
                locationUrl = "https://example1.com",
                startsAtDate = startsAt,
                talkUrl = "https://example2.com",
                timeZoneName = "Europe/Berlin",
                timeZoneOffset = ZoneOffset.UTC,
                typeDescription = "Textual description of the shift type",
                typeId = 42,
                typeName = "Name of the shift type",
            )
        ).isNotNull()
    }

}
