package dev.vladimir.details.data.mapper

import dev.vladimir.details.data.response.movie_actor.MovieActorsResponseModel
import dev.vladimir.details.domain.model.Actor
import javax.inject.Inject

class MovieActorsMapper @Inject constructor() {

    fun mapMovieActors(movieActorsResponseModel: MovieActorsResponseModel): List<Actor> =
        movieActorsResponseModel.cast.map {
            Actor(
                name = it.name,
                actorPhotoPath = it.profilePath
            )
        }
}
