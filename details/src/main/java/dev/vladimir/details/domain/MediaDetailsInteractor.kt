package dev.vladimir.details.domain

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.details.domain.model.MediaDetailsModel
import dev.vladimir.details.domain.repository.IMediaDetailRepository
import javax.inject.Inject

private const val MOVIE_MEDIA_TYPE = "0"
private const val TV_MEDIA_TYPE = "1"

class MediaDetailsInteractor @Inject constructor(
    private val iMediaDetailRepository: IMediaDetailRepository,
) {

    suspend fun getMediaDetails(mediaId: String, mediaType: String): Result<MediaDetailsModel> =
        when (mediaType) {
            MOVIE_MEDIA_TYPE -> getMovieDetails(mediaId)
            TV_MEDIA_TYPE -> getTvDetails(mediaId)
            else -> Result.Error()
        }

    private suspend fun getMovieDetails(movieId: String): Result<MediaDetailsModel> =
        iMediaDetailRepository.getMovieDetails(movieId)

    private suspend fun getTvDetails(tvId: String): Result<MediaDetailsModel> =
        iMediaDetailRepository.getTvDetails(tvId)

}
