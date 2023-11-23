package info.metadude.kotlin.library.engelsystem

import info.metadude.kotlin.library.engelsystem.models.Shift
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EngelsystemService {

    @GET("{path}")
    suspend fun getShifts(
        @Path("path", encoded = true) path: String,
        @Query("key") apiKey: String
    ): Response<List<Shift>>

}
