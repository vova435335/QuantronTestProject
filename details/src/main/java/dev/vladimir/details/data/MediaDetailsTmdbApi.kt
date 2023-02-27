package dev.vladimir.details.data

import dev.vladimir.core.data.common.DEFAULT_LANGUAGE
import dev.vladimir.core.data.common.LANGUAGE_QUERY_KEY
import dev.vladimir.details.data.response.movi.MovieDetailsResponseModel
import dev.vladimir.details.data.response.movie_actor.MovieActorsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID_PATH_PARAM_KEY = "movie_id"

interface MediaDetailsTmdbApi {

    @GET("/3/movie/{$MOVIE_ID_PATH_PARAM_KEY}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query(LANGUAGE_QUERY_KEY) language: String = DEFAULT_LANGUAGE
    ): Response<MovieDetailsResponseModel>

    @GET("/3/movie/{$MOVIE_ID_PATH_PARAM_KEY}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: String,
        @Query(LANGUAGE_QUERY_KEY) language: String = DEFAULT_LANGUAGE
    ): Response<MovieActorsResponseModel>

}
