package dev.gtxtreme.bmsassignment.data.source

import dev.gtxtreme.bmsassignment.data.model.response.MovieCreditsNetworkResponse
import dev.gtxtreme.bmsassignment.data.model.response.MovieDetailNetworkResponse
import dev.gtxtreme.bmsassignment.network.NetworkResponse

class MovieDetailRemoteSourceImpl(private val movieApi: MovieDetailApi) : MovieDetailRemoteSource {


    override suspend fun getMovieDetails(id: String): MovieDetailNetworkResponse {

        return when (val result = movieApi.getMovieDetails(id = id)) {
            is NetworkResponse.Error -> throw Exception(
                result.error?.message ?: "Something went wrong"
            )
            is NetworkResponse.Success -> result.data
        }
    }

    override suspend fun getMovieCredits(id: String): MovieCreditsNetworkResponse {
        return when (val result = movieApi.getMovieCredits(id = id)) {
            is NetworkResponse.Error -> throw Exception(
                result.error?.message ?: "Something went wrong"
            )
            is NetworkResponse.Success -> result.data
        }
    }
}