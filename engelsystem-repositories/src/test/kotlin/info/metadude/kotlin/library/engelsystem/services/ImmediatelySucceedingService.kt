package info.metadude.kotlin.library.engelsystem.services

import info.metadude.kotlin.library.engelsystem.EngelsystemService
import info.metadude.kotlin.library.engelsystem.models.Shift
import retrofit2.Response
import retrofit2.Response.success

class ImmediatelySucceedingService : EngelsystemService {

    override suspend fun getShifts(
        eTag: String,
        lastModifiedAt: String,
        path: String,
        apiKey: String,
    ): Response<List<Shift>> =
        success(listOf(Shift()))

}
