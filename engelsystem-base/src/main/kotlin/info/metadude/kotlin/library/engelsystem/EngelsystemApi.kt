package info.metadude.kotlin.library.engelsystem

import okhttp3.Call

interface EngelsystemApi {

    fun provideEngelsystemService(
        baseUrl: String,
        callFactory: Call.Factory,
    ): EngelsystemService

}
