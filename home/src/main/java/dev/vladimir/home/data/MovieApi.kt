package dev.vladimir.home.data

import dev.vladimir.home.data.response.PopularMovieResponseModel
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMovieResponseModel
}
