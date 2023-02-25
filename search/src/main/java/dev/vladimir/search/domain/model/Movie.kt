package dev.vladimir.search.domain.model

import dev.vladimir.search.data.paging.MediaType

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val mediaType: MediaType
)
