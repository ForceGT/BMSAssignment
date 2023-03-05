package dev.gtxtreme.bmsassignment.data.repository

import dev.gtxtreme.bmsassignment.data.source.MovieDetailRemoteSource
import dev.gtxtreme.bmsassignment.domain.mapper.MovieDetailsMapper
import dev.gtxtreme.bmsassignment.domain.model.MovieDetails

class MovieDetailRepositoryImpl(private val remoteSource: MovieDetailRemoteSource) :
    MovieDetailRepository {
    override suspend fun getMovieDetail(id: String): MovieDetails {
        val movieDetails = remoteSource.getMovieDetails(id = id)
        val movieCredits = remoteSource.getMovieCredits(id = id)
        return MovieDetailsMapper.mapToDomain(
            movieDetailsResponse = movieDetails,
            movieCreditResponse = movieCredits
        )
    }
}