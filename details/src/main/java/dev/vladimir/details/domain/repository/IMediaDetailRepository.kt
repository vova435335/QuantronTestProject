package dev.vladimir.details.domain.repository

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.details.domain.model.MediaModel

interface IMediaDetailRepository {

    suspend fun getMovieDetails(movieId: String): Result<MediaModel>
}
