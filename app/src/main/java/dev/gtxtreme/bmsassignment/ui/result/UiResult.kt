package dev.gtxtreme.bmsassignment.ui.result

import dev.gtxtreme.bmsassignment.domain.model.GenericResult

sealed interface UiResult<out S, out E> {

    data class Success<S>(val data: S) : UiResult<S, Nothing>
    data class Error<E>(val error: E) : UiResult<Nothing, E>
    object Loading : UiResult<Nothing, Nothing>
    object Empty: UiResult<Nothing, Nothing>
}