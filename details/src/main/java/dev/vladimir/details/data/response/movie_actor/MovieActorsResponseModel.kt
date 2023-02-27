package dev.vladimir.details.data.response.movie_actor


import com.google.gson.annotations.SerializedName

data class MovieActorsResponseModel(
    @SerializedName("cast") val cast: List<Cast>,
    @SerializedName("crew") val crew: List<Crew>,
    @SerializedName("id") val id: Int
)