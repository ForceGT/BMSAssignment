package dev.gtxtreme.bmsassignment.data.source

import dev.gtxtreme.bmsassignment.data.model.response.MovieCreditsNetworkResponse
import dev.gtxtreme.bmsassignment.data.model.response.MovieDetailNetworkResponse
import dev.gtxtreme.bmsassignment.network.NetworkManager
import dev.gtxtreme.bmsassignment.network.NetworkResponse

class MovieDetailApi(private val networkManager: NetworkManager) {
    suspend fun getMovieDetails(id: String): NetworkResponse<MovieDetailNetworkResponse> {
        val URL = "/movie/$id"
        return networkManager.makeGETRequest(url = URL)
    }

    suspend fun getMovieCredits(id: String): NetworkResponse<MovieCreditsNetworkResponse> {
        val URL = "/movie/$id/credits"
        return networkManager.makeGETRequest(url = URL)
    }

}