package dev.vladimir.home.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.vladimir.home.data.paging.PopularMoviePagingSource
import dev.vladimir.home.domain.model.Movie
import dev.vladimir.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class MovieInteractor @Inject constructor(
    private val iHomeRepository: IHomeRepository,
) {

    fun getPagingPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviePagingSource(
                    loader = { page ->
                        getPopularMovie(page)
                    }
                )
            }
        ).flow
    }


    private suspend fun getPopularMovie(page: Int) = iHomeRepository.getPopularMovies(page)
}
