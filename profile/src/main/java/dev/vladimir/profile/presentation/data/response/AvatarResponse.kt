package dev.vladimir.profile.presentation.data.response

import com.google.gson.annotations.SerializedName

data class AvatarResponse(
    @SerializedName("gravatar") val gravatar: GravatarResponse,
)
