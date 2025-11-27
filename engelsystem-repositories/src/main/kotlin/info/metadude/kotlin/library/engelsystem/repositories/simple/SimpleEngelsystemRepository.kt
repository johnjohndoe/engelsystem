package info.metadude.kotlin.library.engelsystem.repositories.simple

import info.metadude.kotlin.library.engelsystem.Api
import info.metadude.kotlin.library.engelsystem.EngelsystemApi
import info.metadude.kotlin.library.engelsystem.repositories.EngelsystemRepository
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState.Error
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState.Failure
import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Call
import okhttp3.OkHttpClient

class SimpleEngelsystemRepository(
    private val callFactory: Call.Factory = OkHttpClient.Builder().build(),
    private val api: EngelsystemApi = Api,
) : EngelsystemRepository {

    private companion object {
        const val HEADER_NAME_ETAG = "ETag"
        const val HEADER_NAME_LAST_MODIFIED = "Last-Modified"
    }

    override suspend fun getShiftsState(
        requestETag: String,
        requestLastModifiedAt: String,
        baseUrl: String,
        path: String,
        apiKey: String
    ): Flow<GetShiftsState> {
        return flow {
            val emission = try {
                val response = api
                    .provideEngelsystemService(baseUrl, callFactory)
                    .getShifts(requestETag, requestLastModifiedAt, path, apiKey)
                if (response.isSuccessful) {
                    Success(
                        shifts = response.body().orEmpty(),
                        responseETag = response.headers()[HEADER_NAME_ETAG].orEmpty(),
                        responseLastModifiedAt = response.headers()[HEADER_NAME_LAST_MODIFIED].orEmpty(),
                    )
                } else {
                    val errorMessage = response.errorBody()?.string() ?: response.message().orEmpty()
                    Error(
                        httpStatusCode = response.code(),
                        errorMessage = errorMessage,
                    )
                }
            } catch (t: Throwable) {
                Failure(throwable = t)
            }
            emit(emission)
        }
    }
}
