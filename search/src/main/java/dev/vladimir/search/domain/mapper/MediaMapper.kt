package dev.vladimir.search.domain.mapper

import dev.vladimir.core.utils.StringProvider
import dev.vladimir.search.R
import dev.vladimir.search.data.paging.MediaType
import dev.vladimir.search.domain.model.Media
import dev.vladimir.search.domain.model.Movie
import javax.inject.Inject

private const val FIRST_PAGE = 1

class MediaMapper @Inject constructor(
    private val stringProvider: StringProvider,
) {

    fun mapMedias(movies: List<Movie>, page: Int): List<Media> {
        val media = movies.map(::mapMedia)
        val mediaType = media.first().mediaType

        return if (page == FIRST_PAGE) {
            val titleRes = when (mediaType) {
                MediaType.MOVIE -> R.string.movie_header_title
                MediaType.TV -> R.string.tv_header_title
            }

            return listOf(Media.Header(stringProvider.getString(titleRes))) + media
        } else {
            media
        }

    }

    private fun mapMedia(movie: Movie): Media.Movie =
        with(movie) {
            Media.Movie(
                id = id,
                title = title,
                posterPath = posterPath,
                mediaType = mediaType
            )
        }
}
