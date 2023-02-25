package dev.vladimir.search.data

import dev.vladimir.core.data.common.DEFAULT_LANGUAGE
import dev.vladimir.core.data.common.LANGUAGE_QUERY_KEY
import dev.vladimir.core.data.common.PAGE_QUERY_KEY
import dev.vladimir.core.data.response.MoviesResponseModel
import dev.vladimir.search.data.response.TvResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val SEARCH_QUERY_KEY = "query"

interface MovieApi {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query(PAGE_QUERY_KEY) page: Int,
        @Query(SEARCH_QUERY_KEY) query: String,
        @Query(LANGUAGE_QUERY_KEY) language: String = DEFAULT_LANGUAGE,
    ): MoviesResponseModel

    @GET("search/tv")
    suspend fun searchTv(
        @Query(PAGE_QUERY_KEY) page: Int,
        @Query(SEARCH_QUERY_KEY) query: String,
        @Query(LANGUAGE_QUERY_KEY) language: String = DEFAULT_LANGUAGE,
    ): TvResponseModel
}
