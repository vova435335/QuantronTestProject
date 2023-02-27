package dev.vladimir.home.domain.repository

import dev.vladimir.home.domain.model.Media

interface IHomeRepository {

    suspend fun getPopularMovies(page: Int): List<Media>

    suspend fun getPopularTv(page: Int): List<Media>
}
