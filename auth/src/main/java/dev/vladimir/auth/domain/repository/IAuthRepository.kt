package dev.vladimir.auth.domain.repository

import dev.vladimir.auth.data.model.User
import dev.vladimir.core.data.common.models.Result

interface IAuthRepository {

    suspend fun login(user: User): Result<Unit>
}
