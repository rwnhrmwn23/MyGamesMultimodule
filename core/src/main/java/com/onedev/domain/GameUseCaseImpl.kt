package com.onedev.domain

import com.onedev.data.model.Games
import com.onedev.data.model.GamesDetail
import com.onedev.data.repository.GameRepository
import com.onedev.utils.StateEvent
import kotlinx.coroutines.flow.Flow

class GameUseCaseImpl(
    private val gameRepository: GameRepository
) : GameUseCase {
    override fun games(search : String): Flow<StateEvent<Games>> {
        return gameRepository.games(search)
    }

    override fun gamesDetail(id: Int): Flow<StateEvent<GamesDetail>> {
        return gameRepository.gamesDetail(id)
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

    override fun checkGameIsFavorite(id: Int): Flow<Games.Result> {
        return gameRepository.checkGameIsFavorite(id)
    }
}