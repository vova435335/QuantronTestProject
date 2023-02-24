package dev.vladimir.home.domain.repository

import dev.vladimir.home.domain.model.Movie

interface IHomeRepository {

    suspend fun getPopularMovies(): List<Movie>
}
