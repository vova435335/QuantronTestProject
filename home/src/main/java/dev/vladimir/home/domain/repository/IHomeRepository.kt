package dev.vladimir.home.domain.repository

import dev.vladimir.home.data.response.PopularMovieResponseModel

interface IHomeRepository {

    suspend fun getPopularMovies(): PopularMovieResponseModel
}
