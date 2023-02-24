package dev.vladimir.home.data.repository

import dev.vladimir.home.data.MovieApi
import dev.vladimir.home.domain.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieApi: MovieApi,
) : IHomeRepository {

    override suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        movieApi.getPopularMovies()
    }
}
