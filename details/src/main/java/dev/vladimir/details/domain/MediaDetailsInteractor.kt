package dev.vladimir.details.domain

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.details.domain.model.MediaDetailsModel
import dev.vladimir.details.domain.repository.IMediaDetailRepository
import javax.inject.Inject

class MediaDetailsInteractor @Inject constructor(
    private val iMediaDetailRepository: IMediaDetailRepository,
) {

    suspend fun getMovieDetails(movieId: String): Result<MediaDetailsModel> =
        iMediaDetailRepository.getMovieDetails(movieId)
}
