package com.onedev.network.data.repository

import com.onedev.network.data.model.Games
import com.onedev.network.utils.ApiResponse
import com.onedev.network.utils.NetworkResource
import com.onedev.network.source.NetworkDataSource
import com.onedev.network.utils.StateEvent
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val networkDataSource: NetworkDataSource
): GameRepository {
    override fun games(): Flow<StateEvent<List<Games>>> =
        object : NetworkResource<List<Games>>() {
            override suspend fun createCall(): Flow<ApiResponse<List<Games>>> {
                return networkDataSource.games()
            }

        }.asFlow()
}