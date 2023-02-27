package dev.vladimir.home.data.repository

import dev.vladimir.home.data.PopularMediaTmdbApi
import dev.vladimir.home.data.mapper.PopularMovieMapper
import dev.vladimir.home.data.mapper.PopularTvMapper
import dev.vladimir.home.domain.model.Media
import dev.vladimir.home.domain.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val popularMediaTmdbApi: PopularMediaTmdbApi,
    private val popularMovieMapper: PopularMovieMapper,
    private val popularTvMapper: PopularTvMapper
) : IHomeRepository {

    override suspend fun getPopularMovies(page: Int) =
        withContext(Dispatchers.IO) {
            popularMovieMapper.mapMovies(popularMediaTmdbApi.getPopularMovies(page))
        }

    override suspend fun getPopularTv(page: Int): List<Media> =
        popularTvMapper.mapTvList(popularMediaTmdbApi.getPopularTv(page))
}
