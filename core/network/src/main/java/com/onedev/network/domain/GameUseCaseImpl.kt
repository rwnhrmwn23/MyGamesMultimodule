package com.onedev.network.domain

import com.onedev.network.data.model.Games
import com.onedev.network.data.repository.GameRepository
import com.onedev.network.utils.StateEvent
import kotlinx.coroutines.flow.Flow

class GameUseCaseImpl(
    private val gameRepository: GameRepository
) : GamesUseCase{
    override fun games(): Flow<StateEvent<List<Games>>> {
        return gameRepository.games()
    }
}