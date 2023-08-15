package com.onedev.network

import com.onedev.data.model.Games
import com.onedev.data.model.GamesDetail
import com.onedev.utils.ApiResponse
import com.onedev.network.service.ApiService
import com.onedev.utils.Mapper.mapToGames
import com.onedev.network.service.asFlowStateEvent
import com.onedev.utils.Mapper.mapToGamesDetail
import kotlinx.coroutines.flow.Flow

class NetworkDataSource(
    private val apiService: ApiService
) {
    suspend fun games(search : String): Flow<ApiResponse<Games>> {
        return apiService.games(search).asFlowStateEvent {
            it.mapToGames()
        }
    }

    suspend fun gamesDetail(id : Int): Flow<ApiResponse<GamesDetail>> {
        return apiService.gamesDetail(id).asFlowStateEvent {
            it.mapToGamesDetail()
        }
    }
}