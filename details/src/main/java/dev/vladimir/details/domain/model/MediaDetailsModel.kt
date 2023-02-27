package dev.vladimir.details.domain.model

data class MediaDetailsModel(
    val title: String,
    val releaseDate: String,
    val runtime: String,
    val genres: List<String>,
    val posterPath: String,
    val overview: String,
    val actors: List<Actor>,
)
