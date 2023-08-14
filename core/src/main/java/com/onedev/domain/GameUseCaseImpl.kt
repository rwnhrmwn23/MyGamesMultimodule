package com.onedev.domain

import com.onedev.data.model.Games
import com.onedev.data.repository.GameRepository
import com.onedev.utils.StateEvent
import kotlinx.coroutines.flow.Flow

class GameUseCaseImpl(
    private val gameRepository: GameRepository
) : GamesUseCase {
    override fun games(): Flow<StateEvent<Games>> {
        return gameRepository.games()
    }

    override suspend fun addGames(games: Games.Result) {
        return gameRepository.addGames(games)
    }

    override suspend fun deleteGames(games: Games.Result) {
        return gameRepository.deleteGames(games)
    }

    override fun readAllData(): Flow<Games> {
        return gameRepository.readAllData()
    }
}