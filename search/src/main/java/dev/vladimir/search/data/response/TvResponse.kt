package dev.vladimir.search.data.response

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
