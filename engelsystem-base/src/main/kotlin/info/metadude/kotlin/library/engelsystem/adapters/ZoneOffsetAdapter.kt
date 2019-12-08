package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.FromJson
import org.threeten.bp.DateTimeException
import org.threeten.bp.ZoneOffset

class ZoneOffsetAdapter {

    @FromJson
    fun fromJson(jsonValue: String?) = jsonValue?.let {
        try {
            ZoneOffset.of(jsonValue)
        } catch (e: DateTimeException) {
            println(e.message)
            null
        }
    }

}
