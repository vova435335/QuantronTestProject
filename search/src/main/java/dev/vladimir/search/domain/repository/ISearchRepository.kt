package dev.vladimir.search.domain.repository

import dev.vladimir.search.domain.model.Movie

interface ISearchRepository {

    suspend fun searchMovie(page: Int, query: String): List<Movie>

    suspend fun searchTv(page: Int, query: String): List<Movie>
}
