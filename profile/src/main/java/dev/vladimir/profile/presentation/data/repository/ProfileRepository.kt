package dev.vladimir.profile.presentation.data.repository

import dev.vladimir.core.R.string.internet_connection_error_message
import dev.vladimir.core.R.string.unexpected_error_message
import dev.vladimir.core.data.common.map
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.data.common.toResult
import dev.vladimir.core.utils.StringProvider
import dev.vladimir.profile.presentation.data.ProfileTmdbApi
import dev.vladimir.profile.presentation.data.mapper.ProfileMapper
import dev.vladimir.profile.presentation.domain.model.Profile
import dev.vladimir.profile.presentation.domain.repository.IProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileTmdbApi: ProfileTmdbApi,
    private val profileMapper: ProfileMapper,
    private val stringProvider: StringProvider,
) : IProfileRepository {

    override fun getProfile(): Flow<Result<Profile>> = flow {
        val unexpectedErrorMessage = stringProvider.getString(unexpected_error_message)
        val internetConnectionErrorMessage =
            stringProvider.getString(internet_connection_error_message)

        try {
            when (val profile = profileTmdbApi.getProfile().toResult()) {
                is Result.Success -> emit(profile.map(ifSuccess = { profileMapper.mapProfile(it) }))
                else -> emit(profile.map(ifError = { unexpectedErrorMessage }))
            }
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage ?: unexpectedErrorMessage))
        } catch (e: IOException) {
            emit(Result.Error(internetConnectionErrorMessage))
        }
    }.flowOn(Dispatchers.IO)
}