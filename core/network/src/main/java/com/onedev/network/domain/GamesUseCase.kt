package com.onedev.network.domain

import com.onedev.network.data.model.Games
import com.onedev.network.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun games(): Flow<StateEvent<List<Games>>>
}