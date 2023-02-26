package dev.vladimir.details.data

import dev.vladimir.details.data.response.MovieDetailsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsTmdbApi {

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): Response<MovieDetailsResponseModel>
}
