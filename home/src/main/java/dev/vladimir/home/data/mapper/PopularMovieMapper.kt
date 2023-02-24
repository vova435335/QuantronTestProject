package dev.vladimir.home.data.mapper

import dev.vladimir.home.BuildConfig
import dev.vladimir.home.data.response.MovieResponse
import dev.vladimir.home.data.response.PopularMovieResponseModel
import dev.vladimir.home.domain.model.Movie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() {

    fun mupMovies(popularMovieResponseModel: PopularMovieResponseModel): List<Movie> =
        popularMovieResponseModel.movies.map(::mapMovie)

    private fun mapMovie(movieResponse: MovieResponse): Movie =
        Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            posterPath = BuildConfig.POSTER_BASE_URL + movieResponse.posterPath
        )
}
