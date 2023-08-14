package com.onedev.network.response

data class GamesResponse(
    val count: Int?,
    val next: String?,
    val previous: Any?,
    val results: List<Result?>?
) {
    data class Result(
        val background_image: String?,
        val genres: List<Genre?>?,
        val id: Int?,
        val name: String?,
        val parent_platforms: List<ParentPlatform>?,
        val rating: Double?,
        val rating_top: Int?,
        val released: String?,
        val short_screenshots: List<ShortScreenshot>?,
        val stores: List<Store>?,
        val tags: List<Tag>?,
    ) {
        data class Genre(
            val id: Int?,
            val name: String?
        )

        data class ParentPlatform(
            val platform: Platform?
        ) {
            data class Platform(
                val id: Int?,
                val name: String?
            )
        }

        data class ShortScreenshot(
            val id: Int?,
            val image: String?
        )

        data class Store(
            val id: Int?,
            val store: Store?
        ) {
            data class Store(
                val id: Int?,
                val name: String?
            )
        }

        data class Tag(
            val id: Int?,
            val name: String?
        )
    }
}