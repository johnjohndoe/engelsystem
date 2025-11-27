package info.metadude.kotlin.library.engelsystem

import okhttp3.Call
import okhttp3.OkHttpClient

interface EngelsystemApi {

    fun provideEngelsystemService(
        baseUrl: String,
        callFactory: Call.Factory = OkHttpClient.Builder().build(),
    ): EngelsystemService

}
