package dev.vladimir.auth.data.repository

import dev.vladimir.auth.data.TmdbAuthApi
import dev.vladimir.auth.data.model.User
import dev.vladimir.auth.data.request.RequestSessionBody
import dev.vladimir.auth.data.request.RequestTokenBody
import dev.vladimir.session.data.storage.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Сохранение в сторедж вынести в interactor

class ProfileRepository @Inject constructor(
    private val tmdbAuthApi: TmdbAuthApi,
    private val sessionStorage: SessionStorage
) {

    suspend fun login(user: User = User(login = "435335vova", password = "vova7385")) {
        withContext(Dispatchers.IO) {
            val token = tmdbAuthApi.createRequestToken()
            val validateToken = tmdbAuthApi.validateRequestTokenWithLogin(
                RequestTokenBody(
                    username = user.login,
                    password = user.password,
                    requestToken = token.requestToken
                )
            )
            val session = tmdbAuthApi.createSessionId(
                RequestSessionBody(
                    requestToken = validateToken.requestToken
                )
            )

            sessionStorage.saveSessionId(session.sessionId)

        }
    }
}


