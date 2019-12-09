package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.FromJson
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeParseException

class InstantAdapter {

    @FromJson
    fun fromJson(jsonValue: Long?) = jsonValue?.let {
        try {
            Instant.ofEpochSecond(jsonValue)
        } catch (e: DateTimeParseException) {
            println(e.message)
            null
        }
    }

}
