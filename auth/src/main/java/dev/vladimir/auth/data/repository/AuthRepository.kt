package dev.vladimir.auth.data.repository

import dev.vladimir.auth.data.TmdbAuthApi
import dev.vladimir.auth.data.model.User
import dev.vladimir.auth.data.request.RequestSessionBody
import dev.vladimir.auth.data.request.RequestTokenBody
import dev.vladimir.auth.domain.repository.IAuthRepository
import dev.vladimir.core.data.common.models.Result
import dev.vladimir.core.data.common.toResult
import dev.vladimir.session.data.storage.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val tmdbAuthApi: TmdbAuthApi,
    private val sessionStorage: SessionStorage,
) : IAuthRepository {

     override suspend fun login(user: User): Result<Unit> =
        withContext(Dispatchers.IO) {
            when (val token = tmdbAuthApi.createRequestToken().toResult()) {
                is Result.Success -> validateToken(token.data.requestToken, user)
                is Result.Error -> Result.Error(token.error)
            }
        }

    private suspend fun validateToken(requestToken: String, user: User): Result<Unit> {
        val validateToken = tmdbAuthApi.validateRequestTokenWithLogin(
            RequestTokenBody(
                username = user.login,
                password = user.password,
                requestToken = requestToken
            )
        ).toResult()

        return when (validateToken) {
            is Result.Success -> createSessionId(validateToken.data.requestToken)
            is Result.Error -> Result.Error(validateToken.error)
        }
    }

    private suspend fun createSessionId(validateToken: String): Result<Unit> {
        val session = tmdbAuthApi.createSessionId(
            RequestSessionBody(
                requestToken = validateToken
            )
        ).toResult()

        return when (session) {
            is Result.Success -> {
                if (session.data.success) {
                    sessionStorage.saveSessionId(session.data.sessionId)
                    Result.Success(Unit)
                } else {
                    Result.Error()
                }
            }
            is Result.Error -> Result.Error(session.error)
        }
    }
}
