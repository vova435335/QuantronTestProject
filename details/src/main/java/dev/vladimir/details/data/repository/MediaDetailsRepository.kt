package dev.vladimir.details.data.repository

import dev.vladimir.core.R
import dev.vladimir.core.data.common.map
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.data.common.toResult
import dev.vladimir.core.utils.StringProvider
import dev.vladimir.details.data.MediaDetailsTmdbApi
import dev.vladimir.details.data.mapper.MovieActorsMapper
import dev.vladimir.details.data.mapper.MovieDetailsMapper
import dev.vladimir.details.data.mapper.TvActorsMapper
import dev.vladimir.details.data.mapper.TvDetailsMapper
import dev.vladimir.details.domain.model.Actor
import dev.vladimir.details.domain.model.MediaDetailsModel
import dev.vladimir.details.domain.repository.IMediaDetailRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MediaDetailsRepository @Inject constructor(
    private val mediaDetailsTmdbApi: MediaDetailsTmdbApi,
    private val moveDetailsMapper: MovieDetailsMapper,
    private val movieActorsMapper: MovieActorsMapper,
    private val tvDetailsMapper: TvDetailsMapper,
    private val tvActorsMapper: TvActorsMapper,
    stringProvider: StringProvider,
) : IMediaDetailRepository {

    private val unexpectedErrorMessage = stringProvider.getString(R.string.unexpected_error_message)
    private val internetConnectionErrorMessage =
        stringProvider.getString(R.string.internet_connection_error_message)

    override suspend fun getMovieDetails(movieId: String): Result<MediaDetailsModel> {
        val actors = getMovieActors(movieId)

        return try {
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

    override suspend fun getTvDetails(tvId: String): Result<MediaDetailsModel> {
        val actors = getTvActors(tvId)

        return try {
            when (val movieDetails = mediaDetailsTmdbApi.getTvDetails(tvId).toResult()) {
                is Result.Success -> movieDetails.map(ifSuccess = {
                    tvDetailsMapper.mapTvDetails(
                        tvDetailsResponseModel = it,
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

    private suspend fun getMovieActors(movieId: String): List<Actor> =
        try {
            when (val actors = mediaDetailsTmdbApi.getMovieActors(movieId).toResult()) {
                is Result.Success -> movieActorsMapper.mapMovieActors(actors.data)
                is Result.Error -> emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }

    private suspend fun getTvActors(tvId: String): List<Actor> =
        try {
            when (val actors = mediaDetailsTmdbApi.getTvActors(tvId).toResult()) {
                is Result.Success -> tvActorsMapper.mapTvActors(actors.data)
                is Result.Error -> emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }


}
