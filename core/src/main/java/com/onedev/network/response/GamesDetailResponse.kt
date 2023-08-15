package com.onedev.network.response

data class GamesDetailResponse(
    val background_image: String?,
    val description_raw: String?,
    val genres: List<Genre>?,
    val id: Int?,
    val name: String?,
    val name_original: String?,
    val playtime: Int?,
    val publishers: List<Publisher>?,
    val rating: Double?,
    val rating_top: Int?,
    val released: String?,
    val tags: List<Tag>?,
    val website: String?
) {
    data class Genre(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val name: String?,
        val slug: String?
    )

    data class Publisher(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val name: String?,
        val slug: String?
    )

    data class Tag(
        val games_count: Int?,
        val id: Int?,
        val image_background: String?,
        val language: String?,
        val name: String?,
        val slug: String?
    )
}