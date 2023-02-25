package dev.vladimir.search.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.vladimir.search.data.paging.MediaType
import dev.vladimir.search.data.paging.SearchMoviesPagingSource
import dev.vladimir.search.domain.model.Movie
import dev.vladimir.search.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class SearchInteractor @Inject constructor(
    private val iSearchRepository: ISearchRepository,
) {

    fun getPagingSearchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchMoviesPagingSource(
                    loader = { page, mediaType ->
                        when (mediaType) {
                            MediaType.MOVIE -> getSearchMovie(page, query)
                            MediaType.TV -> getSearchTv(page, query)
                        }
                    }
                )
            }
        ).flow
    }

    private suspend fun getSearchMovie(page: Int, query: String): List<Movie> =
        iSearchRepository.searchMovie(page, query)

    private suspend fun getSearchTv(page: Int, query: String): List<Movie> =
        iSearchRepository.searchTv(page, query)
}
