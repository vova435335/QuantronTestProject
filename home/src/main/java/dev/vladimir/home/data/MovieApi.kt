package dev.vladimir.home.data

import dev.vladimir.home.data.response.PopularMovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val LANGUAGE_QUERY_KEY = "language"
private const val DEFAULT_LANGUAGE = "ru-RU"

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(LANGUAGE_QUERY_KEY) language: String = DEFAULT_LANGUAGE
    ): PopularMovieResponseModel
}
