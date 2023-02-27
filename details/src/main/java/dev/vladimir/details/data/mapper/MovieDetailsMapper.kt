package dev.vladimir.details.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.details.data.response.movi.MovieDetailsResponseModel
import dev.vladimir.details.domain.model.Actor
import dev.vladimir.details.domain.model.MediaDetailsModel
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {

    fun mapMovieDetails(
        movieDetailsResponseModel: MovieDetailsResponseModel,
        actors: List<Actor>,
    ): MediaDetailsModel = MediaDetailsModel(
        title = movieDetailsResponseModel.title,
        releaseDate = movieDetailsResponseModel.releaseDate,
        runtime = movieDetailsResponseModel.runtime.toString(),
        genres = movieDetailsResponseModel.genres.map { it.name },
        posterPath = BuildConfig.POSTER_BASE_URL + movieDetailsResponseModel.posterPath,
        overview = movieDetailsResponseModel.overview,
        actors = actors
    )
}
