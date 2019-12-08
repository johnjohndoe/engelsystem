package info.metadude.kotlin.library.engelsystem.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.Instant

class InstantJsonAdapter : JsonAdapter<Instant>() {

    private val delegate = InstantAdapter()

    override fun fromJson(reader: JsonReader): Instant? {
        val jsonValue = reader.nextLong()
        return delegate.fromJson(jsonValue)
    }

    override fun toJson(writer: JsonWriter, value: Instant?) {
        throw NotImplementedError()
    }

}
