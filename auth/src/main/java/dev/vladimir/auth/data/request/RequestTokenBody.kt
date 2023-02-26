package dev.vladimir.auth.data.request

import com.google.gson.annotations.SerializedName

data class RequestTokenBody(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("request_token") val requestToken: String
)
