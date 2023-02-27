package dev.vladimir.details.data.repository

import dev.vladimir.core.R
import dev.vladimir.core.data.common.map
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.data.common.toResult
import dev.vladimir.core.utils.StringProvider
import dev.vladimir.details.data.MediaDetailsTmdbApi
import dev.vladimir.details.data.mapper.MovieActorsMapper
import dev.vladimir.details.data.mapper.MovieDetailsMapper
import dev.vladimir.details.domain.model.Actor
import dev.vladimir.details.domain.model.MediaDetailsModel
import dev.vladimir.details.domain.repository.IMediaDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MediaDetailsRepository @Inject constructor(
    private val mediaDetailsTmdbApi: MediaDetailsTmdbApi,
    private val moveDetailsMapper: MovieDetailsMapper,
    private val movieActorsMapper: MovieActorsMapper,
    stringProvider: StringProvider,
) : IMediaDetailRepository {

    private val unexpectedErrorMessage = stringProvider.getString(R.string.unexpected_error_message)
    private val internetConnectionErrorMessage =
        stringProvider.getString(R.string.internet_connection_error_message)

    override suspend fun getMovieDetails(movieId: String): Result<MediaDetailsModel> =
        withContext(Dispatchers.IO) {
            val actors = getActors(movieId)

            try {
                when (val movieDetails = mediaDetailsTmdbApi.getMovieDetails(movieId).toResult()) {
                    is Result.Success -> movieDetails.map(ifSuccess = {
                        moveDetailsMapper.mapMovieDetails(
                            movieDetailsResponseModel = it,
                            actors = actors
                        )
                    })
                    is Result.Error -> Result.Error(internetConnectionErrorMessage)
                }
            } catch (e: HttpException) {
                Result.Error(e.localizedMessage ?: unexpectedErrorMessage)
            } catch (e: IOException) {
                Result.Error(internetConnectionErrorMessage)
            }
        }

    private suspend fun getActors(movieId: String): List<Actor> =
        try {
            when (val actors = mediaDetailsTmdbApi.getActors(movieId).toResult()) {
                is Result.Success -> movieActorsMapper.mapMovieActors(actors.data)
                is Result.Error -> emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
}
