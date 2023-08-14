package com.onedev.network.source

import com.onedev.network.data.model.Games
import com.onedev.network.utils.ApiResponse
import com.onedev.network.service.ApiService
import com.onedev.network.utils.asFlowStateEvent
import com.onedev.network.utils.Mapper.mapToGames
import kotlinx.coroutines.flow.Flow

class NetworkDataSource(
    private val apiService: ApiService
) {
    suspend fun games(): Flow<ApiResponse<List<Games>>> {
        return apiService.games().asFlowStateEvent {
            it.mapToGames()
        }
    }
}