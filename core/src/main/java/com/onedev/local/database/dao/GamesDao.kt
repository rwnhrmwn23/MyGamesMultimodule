package com.onedev.local.database.dao

import androidx.room.*
import com.onedev.local.database.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGames(gamesEntity: GamesEntity)

    @Delete
    suspend fun deleteGames(gamesEntity: GamesEntity)

    @Query("SELECT * FROM tb_games")
    fun readAllGame(): Flow<List<GamesEntity>>

    @Query("SELECT * FROM tb_games WHERE gamesId = :id")
    fun checkGameIsFavorite(id: Int): Flow<GamesEntity>

}