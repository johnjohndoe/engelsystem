package info.metadude.kotlin.library.engelsystem

import okhttp3.OkHttpClient

interface EngelsystemApi {

    fun provideEngelsystemService(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): EngelsystemService

}
