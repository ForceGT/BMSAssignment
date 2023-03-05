package dev.gtxtreme.bmsassignment.domain.model

data class MovieDetails(
    val backdropImageUrl: String,
    val movieTitle: String,
    val movieOverview: String,
    val movieCrew: List<PersonDetails>,
    val movieCast: List<PersonDetails>,
    val genres: List<String>
) {

    companion object {
        val EMPTY = MovieDetails(
            backdropImageUrl = "",
            movieTitle = "",
            movieOverview = "",
            movieCrew = emptyList(),
            movieCast = emptyList(),
            genres = emptyList()
        )
    }

    data class PersonDetails(
        val name: String,
        val imageUrl: String
    )
}
