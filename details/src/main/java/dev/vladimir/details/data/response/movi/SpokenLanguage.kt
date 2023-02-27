package dev.vladimir.details.data.response.movi

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("iso_639_1") val iso: String,
    @SerializedName("name") val name: String
)