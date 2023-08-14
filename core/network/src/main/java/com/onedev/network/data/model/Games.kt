package com.onedev.network.data.model

data class Games(
    val count: Int?,
    val results: List<Result>?
) {
    data class Result(
        val background_image: String? = "",
        val id: Int? = 0,
        val name: String? = "",
        val rating: Double?,
        val released: String? = "",
        val short_screenshots: List<String>?= emptyList(),
    )
}
