package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.FromJson
import org.threeten.bp.DateTimeException
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeAdapter {

    @FromJson
    fun fromJson(jsonValue: String?) = jsonValue?.let {
        try {
            ZonedDateTime.parse(jsonValue)
        } catch (e: DateTimeException) {
            println(e.message)
            null
        }
    }

}
