package com.onedev.data.model

data class Games(
    val count: Int? = 0,
    val results: List<Result>? = emptyList()
) {
    data class Result(
        val backgroundImage: String? = "",
        val id: Int? = 0,
        val name: String? = "",
        val rating: Double? = 0.0,
        val released: String? = "",
    )
}
