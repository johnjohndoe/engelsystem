package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeJsonAdapter : JsonAdapter<ZonedDateTime>() {

    private val delegate = ZonedDateTimeAdapter()

    override fun fromJson(reader: JsonReader): ZonedDateTime? {
        val jsonValue = reader.nextLong()
        return delegate.fromJson(jsonValue)
    }

    override fun toJson(writer: JsonWriter, value: ZonedDateTime?) {
        throw NotImplementedError()
    }

}
