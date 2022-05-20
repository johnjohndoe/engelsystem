package info.metadude.kotlin.library.engelsystem

import com.squareup.moshi.Moshi
import info.metadude.kotlin.library.engelsystem.adapters.InstantJsonAdapter
import info.metadude.kotlin.library.engelsystem.adapters.ZoneOffsetJsonAdapter
import info.metadude.kotlin.library.engelsystem.adapters.ZonedDateTimeJsonAdapter
import okhttp3.OkHttpClient
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModule : EngelsystemApi {

    override fun provideEngelsystemService(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): EngelsystemService {
        return createRetrofit(baseUrl, okHttpClient).create(EngelsystemService::class.java)
    }

    private fun provideMoshiBuilder(): Moshi {
        return Moshi.Builder()
            .add(Instant::class.java, InstantJsonAdapter())
            .add(ZonedDateTime::class.java, ZonedDateTimeJsonAdapter())
            .add(ZoneOffset::class.java, ZoneOffsetJsonAdapter())
            .build()
    }

    private fun createRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshiBuilder()))
        .client(okHttpClient)
        .build()

}
