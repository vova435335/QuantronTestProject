package dev.vladimir.search.data.response

import com.google.gson.annotations.SerializedName

data class TvResponseModel(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvList: List<TvResponse>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
)
