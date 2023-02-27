package dev.vladimir.details.domain.model

data class MediaModel(
    val title: String,
    val releaseDate: String,
    val runtime: Int,
    val genres: List<String>,
    val posterPath: String,
    val overview: String,
    val actors: List<Actor>,
)
