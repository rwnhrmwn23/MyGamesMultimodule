package com.onedev.mygamesmultimodule.utils

import com.onedev.data.model.Games
import com.onedev.data.model.GamesDetail
import com.onedev.local.database.entity.GamesEntity
import com.onedev.network.response.GamesResponse

object DataDummy {
    fun generateDataDummyGame(): Games {
        val gameList = ArrayList<Games.Result>()
        for (i in 0..10) {
            val gamesResult = Games.Result(
                backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                id = i,
                name = "Grand Theft Auto V",
                rating = 4.47,
                released = "2013-09-17",
            )
            gameList.add(gamesResult)
        }
        return Games(gameList.size, gameList)
    }
    fun generateDummyGamesResponse(): GamesResponse {
        val gameList = ArrayList<GamesResponse.Result>()
        for (i in 0..10) {
            val games = GamesResponse.Result(
                background_image = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                id = i,
                name = "Grand Theft Auto V",
                rating = 4.47,
                released = "2013-09-17",
            )
            gameList.add(games)
        }
        return GamesResponse(
            count = gameList.size,
            next = "https://api.rawg.io/api/games?key=d084045ca6164bbeb97021752a930416&page=2",
            previous = null,
            results = gameList,
        )
    }

    fun generateNullDataDummyGame(): Games {
        return Games(0, null)
    }

    fun generateDataResultDummyGame(): Games.Result {
        return Games.Result(
            backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
            id = 1,
            name = "Grand Theft Auto V",
            rating = 4.47,
            released = "2013-09-17",
        )
    }

    fun generateNullDataDetailDummyGame(): Games.Result {
        return Games.Result(
            backgroundImage = null,
            id = null,
            name = null,
            rating = null,
            released = null,
        )
    }

    fun generateDetailDummyGame(): GamesDetail {
        return GamesDetail(
            backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
            id = 1,
            name = "Grand Theft Auto V",
            rating = 4.47,
            released = "2013-09-17",
            descriptionRaw = "lorem ipsum",
            genres = "Action",
            playtime = 100,
            publishers = "Rockstar",
            tags = "Adventure",
        )
    }
}