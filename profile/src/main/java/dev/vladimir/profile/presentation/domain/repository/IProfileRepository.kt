package dev.vladimir.profile.presentation.domain.repository

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.profile.presentation.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {

    fun getProfile(): Flow<Result<Profile>>
}
