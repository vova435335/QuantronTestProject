package dev.vladimir.home.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.vladimir.home.data.paging.PopularMediaPagingSource
import dev.vladimir.home.domain.model.Media
import dev.vladimir.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class MovieInteractor @Inject constructor(
    private val iHomeRepository: IHomeRepository,
) {

    fun getPagingPopularMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMediaPagingSource(
                    loaderMovie = { page -> getPopularMovie(page) },
                    loaderTv = { page -> getPopularTv(page) }
                )
            }
        ).flow
    }

    private suspend fun getPopularMovie(page: Int) = iHomeRepository.getPopularMovies(page)

    private suspend fun getPopularTv(page: Int) = iHomeRepository.getPopularTv(page)
}
