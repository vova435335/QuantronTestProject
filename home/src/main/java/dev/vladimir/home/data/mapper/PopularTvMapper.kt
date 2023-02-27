package dev.vladimir.home.data.mapper

import dev.vladimir.core.BuildConfig
import dev.vladimir.home.data.response.tv.TvResponse
import dev.vladimir.home.data.response.tv.TvResponseModel
import dev.vladimir.home.domain.model.Media
import dev.vladimir.home.domain.model.MediaType
import javax.inject.Inject

class PopularTvMapper @Inject constructor() {

    fun mapTvList(tvResponseModel: TvResponseModel): List<Media> =
        tvResponseModel.tvResponse.map(::mapTv)

    private fun mapTv(tvResponse: TvResponse): Media =
        Media(
            id = tvResponse.id,
            title = tvResponse.name,
            posterPath = BuildConfig.POSTER_BASE_URL + tvResponse.posterPath,
            mediaType = MediaType.TV
        )
}