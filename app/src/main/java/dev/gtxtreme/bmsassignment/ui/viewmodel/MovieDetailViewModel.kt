package dev.gtxtreme.bmsassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gtxtreme.bmsassignment.domain.model.GenericResult
import dev.gtxtreme.bmsassignment.domain.model.MovieDetails
import dev.gtxtreme.bmsassignment.domain.usecase.GetMovieDetailUseCase
import dev.gtxtreme.bmsassignment.ui.result.UiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {

    companion object {
        const val ID = "453395"
    }

    private val _movieDetailFlow = MutableStateFlow<UiResult<MovieDetails, String>>(UiResult.Empty)
    val movieDetailFlow = _movieDetailFlow.asStateFlow()

    init {
        getMovieDetails()
    }

    fun getMovieDetails() {
        viewModelScope.launch {
            _movieDetailFlow.emit(UiResult.Loading)
            when (val result = getMovieDetailUseCase(id = ID)) {
                is GenericResult.Error -> {
                    _movieDetailFlow.emit(UiResult.Error(result.error))
                }
                is GenericResult.Success -> {
                    _movieDetailFlow.emit(UiResult.Success(result.data))
                }
            }
        }
    }
}