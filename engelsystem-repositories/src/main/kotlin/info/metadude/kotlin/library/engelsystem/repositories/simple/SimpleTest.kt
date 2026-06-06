package info.metadude.kotlin.library.engelsystem.repositories.simple

import info.metadude.kotlin.library.engelsystem.Api
import kotlinx.coroutines.flow.collectLatest
import okhttp3.OkHttpClient

internal suspend fun main() {
    SimpleEngelsystemRepository(
        callFactory = OkHttpClient.Builder().build(),
        api = Api,
    )
        .getShiftsState(
            requestETag = "",
            requestLastModifiedAt = "",
            url = "https://staging.engelsystem.de/test/shifts-json-export",
            apiKey = "b8bb16fcd23500f2054480faad8df9db0884280fae8242182530e982cf76e87b",
        )
        .collectLatest { println("$it") }

    println("Done.")

}
