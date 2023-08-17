package com.onedev.network.response

data class GamesResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result>?
) {
    data class Result(
        val background_image: String?,
        val id: Int?,
        val name: String?,
        val rating: Double?,
        val released: String?
    )
}