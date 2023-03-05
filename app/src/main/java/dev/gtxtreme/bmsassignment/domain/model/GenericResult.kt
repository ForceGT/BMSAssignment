package dev.gtxtreme.bmsassignment.domain.model

sealed interface GenericResult<out S, out E> {
    data class Success<S>(val data: S) : GenericResult<S, Nothing>
    data class Error<E>(val error: E) : GenericResult<Nothing, E>
}
