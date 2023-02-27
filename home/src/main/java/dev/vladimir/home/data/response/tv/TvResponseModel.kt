package dev.vladimir.home.data.response.tv


import com.google.gson.annotations.SerializedName

data class TvResponseModel(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvResponse: List<TvResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)