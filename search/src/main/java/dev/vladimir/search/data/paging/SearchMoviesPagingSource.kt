package dev.vladimir.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.vladimir.search.domain.model.Media

class SearchMoviesPagingSource(
    private val loader: suspend (page: Int, mediaType: MediaType) -> List<Media>,
) : PagingSource<Int, Media>() {

    private var mediaType = MediaType.MOVIE

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        val page = params.key ?: 1

        return try {
            val media = loader.invoke(page, mediaType)

            val prevKey = if (page == 1) null else page - 1
            var nextKey = if (media.size < params.loadSize) null else page + 1

            if (mediaType == MediaType.MOVIE && nextKey == null) {
                mediaType = MediaType.TV
                nextKey = 1
            }

            return LoadResult.Page(
                data = media,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}
