package dev.vladimir.home.data.mapper

import dev.vladimir.home.data.response.MovieResponse
import dev.vladimir.home.data.response.PopularMovieResponseModel
import dev.vladimir.home.domain.model.Movie
import javax.inject.Inject

private const val EMPTY_STRING = ""

class PopularMovieMapper @Inject constructor() {

    fun mupMovies(popularMovieResponseModel: PopularMovieResponseModel): List<Movie> =
        popularMovieResponseModel.movies.map(::mapMovie)

    private fun mapMovie(movieResponse: MovieResponse): Movie =
        Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            posterPath = movieResponse.posterPath ?: EMPTY_STRING
        )
}