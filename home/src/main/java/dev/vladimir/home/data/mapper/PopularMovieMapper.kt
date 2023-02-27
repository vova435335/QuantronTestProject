package dev.vladimir.home.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.home.data.response.movie.MovieResponse
import dev.vladimir.home.data.response.movie.MoviesResponseModel
import dev.vladimir.home.domain.model.Media
import dev.vladimir.home.domain.model.MediaType
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() {

    fun mapMovies(popularMoviesResponseModel: MoviesResponseModel): List<Media> =
        popularMoviesResponseModel.movies.map(::mapMovie)

    private fun mapMovie(movieResponse: MovieResponse): Media =
        Media(
            id = movieResponse.id,
            title = movieResponse.title,
            posterPath = BuildConfig.POSTER_BASE_URL + movieResponse.posterPath,
            mediaType = MediaType.MOVIE
        )
}
