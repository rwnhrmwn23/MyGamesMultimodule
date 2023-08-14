package com.onedev.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onedev.local.database.dao.GamesDao
import com.onedev.local.database.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}