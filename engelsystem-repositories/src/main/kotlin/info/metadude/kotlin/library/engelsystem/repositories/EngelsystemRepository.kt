package info.metadude.kotlin.library.engelsystem.repositories

import info.metadude.kotlin.library.engelsystem.repositories.models.GetShiftsState
import kotlinx.coroutines.flow.Flow

interface EngelsystemRepository {

    suspend fun getShiftsState(
        requestETag: String,
        requestLastModifiedAt: String,
        baseUrl: String,
        path: String,
        apiKey: String,
    ): Flow<GetShiftsState>

}
