package dev.vladimir.home.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.core.data.response.MovieResponse
import dev.vladimir.core.data.response.MoviesResponseModel
import dev.vladimir.home.domain.model.Movie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() {

    fun mapMovies(popularMoviesResponseModel: MoviesResponseModel): List<Movie> =
        popularMoviesResponseModel.movies.map(::mapMovie)

    private fun mapMovie(movieResponse: MovieResponse): Movie =
        Movie(
            id = movieResponse.id,
            title = movieResponse.title,
            posterPath = BuildConfig.POSTER_BASE_URL + movieResponse.posterPath
        )
}
