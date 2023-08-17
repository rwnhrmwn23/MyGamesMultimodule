package com.onedev.local.database

import com.onedev.local.database.entity.GamesEntity

object DataDummy {
    fun generateDataEntitiesDummyGame(): List<GamesEntity> {
        val gameList = ArrayList<GamesEntity>()
        for (i in 0..10) {
            val gamesResult = GamesEntity(
                backgroundImage = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                gamesId = 909,
                name = "Grand Theft Auto V",
                rating = 4.47,
                released = "2013-09-17",
            )
            gameList.add(gamesResult)
        }
        return gameList
    }
}