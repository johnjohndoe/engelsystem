package info.metadude.kotlin.library.engelsystem.services

import info.metadude.kotlin.library.engelsystem.EngelsystemService
import info.metadude.kotlin.library.engelsystem.models.Shift
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class ImmediatelyFailingService : EngelsystemService {

    override suspend fun getShifts(
        eTag: String,
        lastModifiedAt: String,
        path: String,
        apiKey: String,
    ): Response<List<Shift>> {
        val responseBody = "Service Unavailable.".toResponseBody("plain/text".toMediaType())
        return Response.error(503, responseBody)
    }

}
