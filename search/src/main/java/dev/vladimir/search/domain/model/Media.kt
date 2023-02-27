package dev.vladimir.search.domain.model

import dev.vladimir.search.data.paging.MediaType

sealed class Media {

    data class Movie(
        val id: Int,
        val title: String,
        val posterPath: String,
        val mediaType: MediaType,
    ) : Media()

    data class Header(
        val title: String,
    ) : Media()
}
