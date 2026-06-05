package info.metadude.kotlin.library.engelsystem.repositories.utils

import okhttp3.HttpUrl.Companion.toHttpUrl

internal data class UrlComponents(
    val baseUrl: String,
    val path: String
) {

    companion object {
        fun String.getUrlComponents(): UrlComponents {
            val httpUrl = toHttpUrl()
            val baseUrl = httpUrl.newBuilder().encodedPath("/").build().toString()
            val path = httpUrl.encodedPath.removePrefix("/")
            return UrlComponents(baseUrl = baseUrl, path = path)
        }
    }

}
