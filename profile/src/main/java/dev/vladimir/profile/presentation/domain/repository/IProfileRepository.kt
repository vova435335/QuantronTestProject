package dev.vladimir.profile.presentation.domain.repository

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.profile.presentation.domain.model.Profile

interface IProfileRepository {

    suspend fun getProfile(): Result<Profile>
}
