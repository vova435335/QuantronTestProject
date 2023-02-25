package dev.vladimir.search.data.mappers

import dev.vladimir.core.BuildConfig
import dev.vladimir.search.data.response.TvResponse
import dev.vladimir.search.data.response.TvResponseModel
import dev.vladimir.search.domain.model.Movie
import javax.inject.Inject

class TvMapper @Inject constructor() {

    fun mapTvList(tvResponseModel: TvResponseModel): List<Movie> =
        tvResponseModel.tvList.map(::mapTv)

    private fun mapTv(tvResponse: TvResponse): Movie = Movie(
        id = tvResponse.id,
        title = tvResponse.name,
        posterPath = BuildConfig.POSTER_BASE_URL + tvResponse.posterPath
    )
}
