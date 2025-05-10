package info.metadude.kotlin.library.engelsystem

import com.squareup.moshi.Moshi
import info.metadude.kotlin.library.engelsystem.adapters.InstantJsonAdapter
import info.metadude.kotlin.library.engelsystem.adapters.ZoneOffsetJsonAdapter
import info.metadude.kotlin.library.engelsystem.adapters.ZonedDateTimeJsonAdapter
import okhttp3.Call
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api : EngelsystemApi {

    override fun provideEngelsystemService(
        baseUrl: String,
        callFactory: Call.Factory
    ): EngelsystemService {
        require(baseUrl.isNotEmpty()) { "baseUrl is empty." }
        return createRetrofit(baseUrl, callFactory).create(EngelsystemService::class.java)
    }

    private fun createRetrofit(
        baseUrl: String,
        callFactory: Call.Factory
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshiBuilder()))
        .callFactory(callFactory)
        .build()

    private fun provideMoshiBuilder(): Moshi {
        return Moshi.Builder()
            .add(Instant::class.java, InstantJsonAdapter())
            .add(ZonedDateTime::class.java, ZonedDateTimeJsonAdapter())
            .add(ZoneOffset::class.java, ZoneOffsetJsonAdapter())
            .build()
    }

}
