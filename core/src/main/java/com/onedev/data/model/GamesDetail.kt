package com.onedev.data.model

data class GamesDetail(
    val backgroundImage: String? = "",
    val descriptionRaw: String? = "",
    val genres: String = "",
    val id: Int? = 0,
    val name: String? = "",
    val playtime: Int? = 0,
    val publishers: String = "",
    val rating: Double? = 0.0,
    val released: String? = "",
    val tags: String = "",
)
