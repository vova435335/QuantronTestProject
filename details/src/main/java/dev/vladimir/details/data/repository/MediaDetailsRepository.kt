package dev.vladimir.details.data.repository

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.details.domain.model.MediaModel
import dev.vladimir.details.domain.repository.IMediaDetailRepository

class MediaDetailsRepository : IMediaDetailRepository {

    override suspend fun getMovieDetails(movieId: String): Result<MediaModel> {
        TODO("Not yet implemented")
    }
}