package dev.vladimir.home.data.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponseModel(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movie: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
)