package dev.vladimir.profile.presentation.domain

import dev.vladimir.core.data.common.models.Result
import dev.vladimir.profile.presentation.domain.model.Profile
import dev.vladimir.profile.presentation.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val iProfileRepository: IProfileRepository
) {

    fun getProfile(): Flow<Result<Profile>> = iProfileRepository.getProfile()
}
