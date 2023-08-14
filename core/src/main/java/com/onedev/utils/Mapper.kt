package com.onedev.utils

import com.onedev.data.model.Games
import com.onedev.local.database.entity.GamesEntity
import com.onedev.network.response.GamesResponse

object Mapper {
    fun GamesResponse?.mapToGames(): Games {
        val games: Games
        val gamesResult = ArrayList<Games.Result>()

        this?.results?.map {
            val gamesScreenshot = ArrayList<String>()

            it?.short_screenshots?.map {screenshots ->
               gamesScreenshot.add(screenshots.image.toString())
            }

            gamesResult.add(
                Games.Result(
                    background_image = it?.background_image,
                    id = it?.id,
                    name = it?.background_image,
                    rating = it?.rating,
                    released = it?.background_image,
                    short_screenshots = gamesScreenshot.toString()
                )
            )

        }
        games = Games(this?.count, gamesResult)

        return games
    }

    fun List<GamesEntity>.mapEntitiesToDomains(): Games {
        var games = Games()

        this.map {
            val gamesResult = ArrayList<Games.Result>()

            gamesResult.add(
                Games.Result(
                    background_image = it.backgroundImage,
                    id = it.id,
                    name = it.name,
                    rating = it.rating,
                    released = it.released,
                    short_screenshots = it.shortScreenshots
                )
            )

            games = Games(this.size, gamesResult)
        }

        return games
    }

    fun Games.Result.mapDomainToEntity() = GamesEntity(
        id = 0,
        gamesId = id ?: 0,
        backgroundImage = background_image.toString(),
        name = name.toString(),
        rating = rating ?: 0.0,
        released = released.toString(),
        shortScreenshots = short_screenshots.toString(),
    )
}