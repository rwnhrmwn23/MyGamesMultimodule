package com.onedev.network.data.model

data class GamesDetail(
    val count: Int?,
    val results: List<Result>?
) {
    data class Result(
        val background_image: String? = "",
        val id: Int? = 0,
        val name: String? = "",
        val rating: Double?,
        val rating_top: Int?,
        val released: String? = "",
        val slug: String? = "",
        val stores: List<String>?= emptyList(),
        val tags: List<String>?= emptyList(),
        val short_screenshots: List<String>?= emptyList(),
        val parent_platforms: List<String>? = emptyList(),
        val genres: List<String>? = emptyList(),
    )
}
