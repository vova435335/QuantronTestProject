package dev.vladimir.profile.presentation.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponseModel(
    @SerializedName("avatar") val avatar: AvatarResponse,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    )