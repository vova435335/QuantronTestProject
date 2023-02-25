package dev.vladimir.search.data.mappers

import dev.vladimir.core.BuildConfig
import dev.vladimir.core.data.response.MovieResponse
import dev.vladimir.core.data.response.MoviesResponseModel
import dev.vladimir.search.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapMovies(movieResponseModel: MoviesResponseModel): List<Movie> =
        movieResponseModel.movies.map(::mapMovie)

    private fun mapMovie(mediaResponse: MovieResponse): Movie = Movie(
        id = mediaResponse.id,
        title = mediaResponse.title,
        posterPath = BuildConfig.POSTER_BASE_URL + mediaResponse.posterPath
    )
}
