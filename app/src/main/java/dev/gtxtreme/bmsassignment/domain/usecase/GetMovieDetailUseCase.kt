package dev.gtxtreme.bmsassignment.domain.usecase

import dev.gtxtreme.bmsassignment.data.repository.MovieDetailRepository
import dev.gtxtreme.bmsassignment.domain.model.GenericResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieDetailUseCase(private val movieDetailRepository: MovieDetailRepository) {

    suspend operator fun invoke(id: String) =
        withContext(Dispatchers.Default) {
            try {
                val result = movieDetailRepository.getMovieDetail(id = id)
                return@withContext GenericResult.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext GenericResult.Error(
                    e.message ?: "Something went wrong, please try again"
                )
            }
        }
}
