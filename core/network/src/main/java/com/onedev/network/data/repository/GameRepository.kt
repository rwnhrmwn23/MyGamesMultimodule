package com.onedev.network.data.repository

import com.onedev.network.data.model.Games
import com.onedev.network.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun games(): Flow<StateEvent<List<Games>>>
}