package dev.vladimir.details.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.details.data.response.tv.TvDetailsResponseModel
import dev.vladimir.details.domain.model.Actor
import dev.vladimir.details.domain.model.MediaDetailsModel
import javax.inject.Inject

class TvDetailsMapper @Inject constructor() {

    fun mapTvDetails(
        tvDetailsResponseModel: TvDetailsResponseModel,
        actors: List<Actor>
    ): MediaDetailsModel = MediaDetailsModel(
        title = tvDetailsResponseModel.name,
        releaseDate = tvDetailsResponseModel.firstAirDate,
        runtime = tvDetailsResponseModel.episodeRunTime.firstOrNull().toString(),
        genres = tvDetailsResponseModel.genres.map { it.name },
        posterPath = BuildConfig.POSTER_BASE_URL + tvDetailsResponseModel.posterPath,
        overview = tvDetailsResponseModel.overview,
        actors = actors
    )
}