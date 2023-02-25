package dev.vladimir.home.data

import dev.vladimir.core.data.common.DEFAULT_LANGUAGE
import dev.vladimir.core.data.common.LANGUAGE_QUERY_KEY
import dev.vladimir.core.data.common.PAGE_QUERY_KEY
import dev.vladimir.home.data.response.MoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(PAGE_QUERY_KEY) page: Int,
        @Query(LANGUAGE_QUERY_KEY) language: String = DEFAULT_LANGUAGE
    ): MoviesResponseModel
}
