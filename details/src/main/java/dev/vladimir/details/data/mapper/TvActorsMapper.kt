package dev.vladimir.details.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.details.data.response.tv_actor.TvActorsResponseModel
import dev.vladimir.details.domain.model.Actor
import javax.inject.Inject

class TvActorsMapper @Inject constructor() {

    fun mapTvActors(tvActorsResponseModel: TvActorsResponseModel): List<Actor> =
        tvActorsResponseModel.cast.map {
            Actor(
                id = it.id,
                name = it.name,
                actorPhotoPath = BuildConfig.POSTER_BASE_URL + it.profilePath
            )
        }
}
