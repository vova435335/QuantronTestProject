package dev.vladimir.search.data.repository

import dev.vladimir.search.data.MovieApi
import dev.vladimir.search.data.mappers.MovieMapper
import dev.vladimir.search.data.mappers.TvMapper
import dev.vladimir.search.domain.model.Movie
import dev.vladimir.search.domain.repository.ISearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
    private val tvMapper: TvMapper
) : ISearchRepository {

    override suspend fun searchMovie(page: Int, query: String): List<Movie> =
        withContext(Dispatchers.IO) {
            movieMapper.mapMovies(movieApi.searchMovies(page, query))
        }

    override suspend fun searchTv(page: Int, query: String): List<Movie> =
        withContext(Dispatchers.IO) {
            tvMapper.mapTvList(movieApi.searchTv(page, query))
        }
}
