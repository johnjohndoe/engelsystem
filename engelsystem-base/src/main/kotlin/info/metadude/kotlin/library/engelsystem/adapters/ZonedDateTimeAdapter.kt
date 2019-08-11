package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.FromJson
import info.metadude.kotlin.library.engelsystem.ApiModule
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeParseException

class ZonedDateTimeAdapter {

    @FromJson
    fun fromJson(jsonValue: Long?) = jsonValue?.let {
        try {
            ZonedDateTime.ofInstant(Instant.ofEpochSecond(jsonValue), ApiModule.zoneOffset)
        } catch (e: DateTimeParseException) {
            println(e.message)
            null
        }
    }

}
