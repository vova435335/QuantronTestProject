package dev.vladimir.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.vladimir.home.domain.model.Media
import java.lang.Exception

class PopularMediaPagingSource(
    private val loaderMovie: suspend (page: Int) -> List<Media>,
    private val loaderTv: suspend (page: Int) -> List<Media>,
) : PagingSource<Int, Media>() {

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val page = params.key ?: 1

        return try {
            val movies = loaderMovie.invoke(page)
            val tvList = loaderTv.invoke(page)

            val media = movies + tvList

            return LoadResult.Page(
                data = media.shuffled(),
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(movies.size < params.loadSize && media.size < params.loadSize) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}
