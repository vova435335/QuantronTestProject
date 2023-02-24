package dev.vladimir.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.vladimir.home.domain.model.Movie
import java.lang.Exception

class PopularMoviePagingSource(
    private val loader: suspend (page: Int) -> List<Movie>,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val movies = loader.invoke(page)

            return LoadResult.Page(
                data = movies,
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(movies.size < params.loadSize) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}
