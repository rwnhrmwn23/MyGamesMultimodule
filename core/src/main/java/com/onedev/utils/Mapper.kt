package com.onedev.utils

import com.onedev.data.model.Games
import com.onedev.data.model.GamesDetail
import com.onedev.local.database.entity.GamesEntity
import com.onedev.network.response.GamesDetailResponse
import com.onedev.network.response.GamesResponse

object Mapper {
    fun GamesResponse?.mapToGames(): Games {
        val games: Games
        val gamesResult = ArrayList<Games.Result>()

        this?.results?.map {
            gamesResult.add(
                Games.Result(
                    backgroundImage = it?.background_image,
                    id = it?.id,
                    name = it?.name,
                    rating = it?.rating,
                    released = it?.released,
                )
            )

        }
        games = Games(this?.count, gamesResult)

        return games
    }

    fun GamesDetailResponse?.mapToGamesDetail(): GamesDetail {
        val gameGenres = ArrayList<String>()
        val gamePublishers = ArrayList<String>()
        val gameTags = ArrayList<String>()

        this?.let {
            genres?.map { data ->
                gameGenres.add(data.name.toString())
            }

            publishers?.map { data ->
                gamePublishers.add(data.name.toString())
            }

            tags?.map { data ->
                gameTags.add(data.name.toString())
            }
        }

        return GamesDetail(
            backgroundImage = this?.background_image,
            descriptionRaw = this?.description_raw,
            genres = gameGenres.joinToString(),
            id = this?.id,
            name = this?.name,
            playtime = this?.playtime,
            publishers = gamePublishers.joinToString(),
            rating = this?.rating,
            released = this?.released,
            tags = gameTags.joinToString()
        )
    }

    fun List<GamesEntity>?.mapEntitiesToDomains(): Games {
        var games = Games()
        val gamesResult = ArrayList<Games.Result>()

        this?.map {
            gamesResult.add(
                Games.Result(
                    backgroundImage = it.backgroundImage,
                    id = it.gamesId,
                    name = it.name,
                    rating = it.rating,
                    released = it.released,
                )
            )
            games = Games(this.size, gamesResult)
        }

        return games
    }

    fun GamesEntity?.mapEntityToDomainsResult() = Games.Result(
        backgroundImage = this?.backgroundImage,
        id = this?.gamesId,
        name = this?.name,
        rating = this?.rating,
        released = this?.released,
    )

    fun Games.Result.mapDomainToEntity() = GamesEntity(
        gamesId = id ?: 0,
        backgroundImage = backgroundImage.toString(),
        name = name.toString(),
        rating = rating ?: 0.0,
        released = released.toString(),
    )
}