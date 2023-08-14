package com.onedev.data.repository

import com.onedev.data.model.Games
import com.onedev.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun games(): Flow<StateEvent<Games>>

    suspend fun addGames(games: Games.Result)

    suspend fun deleteGames(games: Games.Result)

    fun readAllData(): Flow<Games>
}