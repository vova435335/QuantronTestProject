package dev.vladimir.details.domain.repository

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.details.domain.model.MediaDetailsModel

interface IMediaDetailRepository {

    suspend fun getMovieDetails(movieId: String): Result<MediaDetailsModel>

    suspend fun getTvDetails(tvId: String): Result<MediaDetailsModel>
}
