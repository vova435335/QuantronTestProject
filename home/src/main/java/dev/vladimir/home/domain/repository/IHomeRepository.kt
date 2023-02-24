package dev.vladimir.home.domain.repository

import dev.vladimir.home.domain.model.Movie

interface IHomeRepository {

    suspend fun getPopularMovies(page: Int): List<Movie>
}
