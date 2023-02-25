package dev.vladimir.search.data.mappers

import dev.vladimir.core.BuildConfig
import dev.vladimir.core.data.response.MovieResponse
import dev.vladimir.core.data.response.MoviesResponseModel
import dev.vladimir.search.domain.model.Movie
import javax.inject.Inject

class MediaMappers @Inject constructor() {

    fun mapMedias(multiSearchResponseModel: MoviesResponseModel): List<Movie> =
        multiSearchResponseModel.movies.map(::mapMedia)

    private fun mapMedia(mediaResponse: MovieResponse): Movie = Movie(
        id = mediaResponse.id,
        title = mediaResponse.title,
        posterPath =  BuildConfig.POSTER_BASE_URL + mediaResponse.posterPath
    )
}
