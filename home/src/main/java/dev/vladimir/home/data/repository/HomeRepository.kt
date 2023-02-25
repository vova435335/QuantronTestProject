package dev.vladimir.home.data.repository

import dev.vladimir.home.data.MovieApi
import dev.vladimir.home.data.mapper.PopularMovieMapper
import dev.vladimir.home.domain.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val popularMovieMapper: PopularMovieMapper
) : IHomeRepository {

    override suspend fun getPopularMovies(page: Int) =
        withContext(Dispatchers.IO) {
            popularMovieMapper.mupMovies(movieApi.getPopularMovies(page = page))
        }
}
