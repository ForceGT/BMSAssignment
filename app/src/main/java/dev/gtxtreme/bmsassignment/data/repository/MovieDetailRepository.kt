package dev.gtxtreme.bmsassignment.data.repository

import dev.gtxtreme.bmsassignment.domain.model.MovieDetails

interface MovieDetailRepository {

    suspend fun getMovieDetail(id: String): MovieDetails
}