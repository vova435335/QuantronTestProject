package dev.vladimir.details.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.details.data.response.movi.MovieDetailsResponseModel
import dev.vladimir.details.domain.model.Actor
import dev.vladimir.details.domain.model.MediaModel
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {

    fun mapMovieDetails(
        movieDetailsResponseModel: MovieDetailsResponseModel,
        actors: List<Actor>,
    ): MediaModel = MediaModel(
        title = movieDetailsResponseModel.title,
        releaseDate = movieDetailsResponseModel.releaseDate,
        runtime = movieDetailsResponseModel.runtime,
        genres = movieDetailsResponseModel.genres.map { it.name },
        posterPath = BuildConfig.POSTER_BASE_URL + movieDetailsResponseModel.posterPath,
        overview = movieDetailsResponseModel.overview,
        actors = actors
    )
}
