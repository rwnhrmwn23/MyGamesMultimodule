package com.onedev.data.repository

import com.onedev.data.model.Games
import com.onedev.data.model.GamesDetail
import com.onedev.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun games(search: String): Flow<StateEvent<Games>>
    fun gamesDetail(id: Int): Flow<StateEvent<GamesDetail>>

    suspend fun addGames(games: Games.Result)

    suspend fun deleteGames(games: Games.Result)

    fun readAllData(): Flow<Games>

    fun checkGameIsFavorite(id: Int): Flow<Games.Result>
}