package info.metadude.kotlin.library.engelsystem.models

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

class ShiftTest {

    @Test
    fun `Assert a shift can be constructed`() {
        assertThat(
            Shift(
                userComment = "comment",
                endsAt = ZonedDateTime.now(),
                locationDescription = "",
                locationUrl = "https://example1.com",
                talkUrl = "https://example2.com",
                timeZoneOffset = ZoneOffset.UTC
            )
        ).isNotNull()
    }

}
