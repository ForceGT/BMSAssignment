package dev.gtxtreme.bmsassignment.domain.mapper

import dev.gtxtreme.bmsassignment.data.model.response.MovieCreditsNetworkResponse
import dev.gtxtreme.bmsassignment.data.model.response.MovieDetailNetworkResponse
import dev.gtxtreme.bmsassignment.domain.model.MovieDetails
import dev.gtxtreme.bmsassignment.network.NetworkManager

object MovieDetailsMapper {

    fun mapToDomain(
        movieDetailsResponse: MovieDetailNetworkResponse,
        movieCreditResponse: MovieCreditsNetworkResponse
    ): MovieDetails {

        val allCast = movieCreditResponse.cast.map {
            MovieDetails.PersonDetails(
                it.name,
                it.getProfileImage()
            )
        }
        val allCrew = movieCreditResponse.crew.map {
            MovieDetails.PersonDetails(
                it.name,
                it.getProfileImage()
            )
        }
        val genres = movieDetailsResponse.genres.map { it.name }
        val backdropImage = NetworkManager.IMAGE_FULL_RESOLUTION_BASE_URL + movieDetailsResponse.backdropPath
        return MovieDetails(
            backdropImageUrl = backdropImage,
            movieTitle = movieDetailsResponse.originalTitle,
            movieOverview = movieDetailsResponse.overview,
            movieCrew = allCrew,
            movieCast = allCast,
            genres = genres
        )
    }
}