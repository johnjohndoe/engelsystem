package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.ZoneOffset

class ZoneOffsetJsonAdapter : JsonAdapter<ZoneOffset>() {

    private val delegate = ZoneOffsetAdapter()

    override fun fromJson(reader: JsonReader): ZoneOffset? {
        val jsonValue = reader.nextString()
        return delegate.fromJson(jsonValue)
    }

    override fun toJson(writer: JsonWriter, value: ZoneOffset?) {
        throw NotImplementedError()
    }

}
