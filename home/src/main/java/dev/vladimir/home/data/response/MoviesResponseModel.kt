package dev.vladimir.home.data.response

import com.google.gson.annotations.SerializedName

data class MoviesResponseModel(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieResponse>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
)
