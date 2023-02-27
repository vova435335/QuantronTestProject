package dev.vladimir.auth.domain

import dev.vladimir.auth.data.model.User
import dev.vladimir.auth.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val iAuthRepository: IAuthRepository,
) {

    suspend fun login(login: String, password: String) =
        iAuthRepository.login(
            User(
                login = login,
                password = password
            )
        )
}
