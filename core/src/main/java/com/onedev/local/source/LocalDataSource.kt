package com.onedev.local.source

import com.onedev.local.database.dao.GamesDao
import com.onedev.local.database.entity.GamesEntity

class LocalDataSource(private val gamesDao: GamesDao) {

    suspend fun addGames(heroEntities: GamesEntity) = gamesDao.addGames(heroEntities)

    suspend fun deleteGames(heroEntities: GamesEntity) = gamesDao.deleteGames(heroEntities)

    fun readAllData() = gamesDao.readAllGame()

}