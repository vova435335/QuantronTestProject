package dev.vladimir.profile.presentation.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.profile.presentation.data.response.ProfileResponseModel
import dev.vladimir.profile.presentation.domain.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapProfile(profileResponseModel: ProfileResponseModel): Profile =
        Profile(
            avatar_path = BuildConfig.POSTER_BASE_URL + '/' + profileResponseModel.avatar.gravatar.hash,
            id = profileResponseModel.id,
            name = profileResponseModel.name,
            username = profileResponseModel.username
        )
}