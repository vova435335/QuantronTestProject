package dev.vladimir.profile.data.request

import com.google.gson.annotations.SerializedName

data class RequestSessionBody(
    @SerializedName("request_token") val requestToken: String
)
