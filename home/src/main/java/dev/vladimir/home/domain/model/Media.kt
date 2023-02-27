package dev.vladimir.home.domain.model

data class Media(
    val id: Int,
    val title: String,
    val posterPath: String,
    val mediaType: MediaType
)