package dev.gtxtreme.bmsassignment.network

import kotlinx.serialization.decodeFromString

sealed class NetworkResponse<out T>(
    val message: String? = null,
    val statusCode: Int = -1
) {
    class Success<T>(
        val data: T,
        statusCode: Int
    ) : NetworkResponse<T>(statusCode = statusCode)

    class Error(
        val error: ErrorResponse? = null,
        val data: Any? = null,
        statusCode: Int
    ) : NetworkResponse<Nothing>(statusCode = statusCode) {
    }

}

data class ErrorResponse(
    val message: String?,
    val errorCode: Int?
)
