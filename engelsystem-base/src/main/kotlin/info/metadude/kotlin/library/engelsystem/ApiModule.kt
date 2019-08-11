package info.metadude.kotlin.library.engelsystem

import com.squareup.moshi.Moshi
import info.metadude.kotlin.library.engelsystem.adapters.ZonedDateTimeJsonAdapter
import okhttp3.OkHttpClient
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModule {

    internal lateinit var zoneOffset: ZoneOffset

    @JvmStatic
    fun provideEngelsystemService(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        zoneOffset: ZoneOffset = ZoneOffset.UTC
    ): EngelsystemService {
        this.zoneOffset = zoneOffset
        return createRetrofit(baseUrl, okHttpClient).create(EngelsystemService::class.java)
    }

    private fun provideMoshiBuilder(): Moshi {
        return Moshi.Builder()
            .add(ZonedDateTime::class.java, ZonedDateTimeJsonAdapter())
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
