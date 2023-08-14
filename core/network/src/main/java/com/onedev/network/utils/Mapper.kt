package com.onedev.network.utils

import com.onedev.network.data.model.Games
import com.onedev.network.data.response.GamesResponse

object Mapper {
    fun GamesResponse?.mapToGames(): List<Games> {
        val games = ArrayList<Games>()

        this?.results?.map {
            val gamesResult = ArrayList<Games.Result>()
            val gamesScreenshot = ArrayList<String>()

            it?.short_screenshots?.map { screenshot ->
                gamesScreenshot.add(screenshot.image.toString())
            }

            gamesResult.add(
                Games.Result(
                    background_image = it?.background_image,
                    id = it?.id,
                    name = it?.background_image,
                    rating = it?.rating,
                    released = it?.background_image,
                    short_screenshots = gamesScreenshot
                )
            )

            games.add(
                Games(count, gamesResult)
            )
        }

        return games
    }
}