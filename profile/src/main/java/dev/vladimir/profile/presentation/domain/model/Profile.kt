package dev.vladimir.profile.presentation.domain.model

data class Profile(
    val avatar_path: String = "",
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
)
