package info.metadude.kotlin.library.engelsystem.repositories.models

import info.metadude.kotlin.library.engelsystem.models.Shift
import java.net.HttpURLConnection

sealed interface GetShiftsState {

    data class Success(
        val shifts: List<Shift>,
        val responseETag: String,
        val responseLastModifiedAt: String,
    ) : GetShiftsState

    data class Error(
        val httpStatusCode: Int,
        val errorMessage: String,
    ) : GetShiftsState {
        val isNotModified = httpStatusCode == HttpURLConnection.HTTP_NOT_MODIFIED
    }

    data class Failure(
        val throwable: Throwable,
    ) : GetShiftsState {
        override fun toString() = "Failure(throwable=$throwable)"
    }

}
