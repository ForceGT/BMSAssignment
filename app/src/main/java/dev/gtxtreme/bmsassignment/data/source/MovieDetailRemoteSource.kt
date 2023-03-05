package dev.gtxtreme.bmsassignment.data.source

import dev.gtxtreme.bmsassignment.data.model.response.MovieCreditsNetworkResponse
import dev.gtxtreme.bmsassignment.data.model.response.MovieDetailNetworkResponse

interface MovieDetailRemoteSource {

    suspend fun getMovieDetails(id: String): MovieDetailNetworkResponse
    suspend fun getMovieCredits(id: String): MovieCreditsNetworkResponse
}