package com.onedev.data.repository

import com.onedev.data.model.Games
import com.onedev.local.source.LocalDataSource
import com.onedev.source.NetworkDataSource
import com.onedev.utils.ApiResponse
import com.onedev.utils.Mapper.mapDomainToEntity
import com.onedev.utils.Mapper.mapEntitiesToDomains
import com.onedev.utils.NetworkResource
import com.onedev.utils.StateEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
): GameRepository {
    override fun games(): Flow<StateEvent<Games>> =
        object : NetworkResource<Games>() {
            override suspend fun createCall(): Flow<ApiResponse<Games>> {
                return networkDataSource.games()
            }

        }.asFlow()

    override suspend fun addGames(games: Games.Result) {
        val userEntity = games.mapDomainToEntity()
        localDataSource.addGames(userEntity)
    }

    override suspend fun deleteGames(games: Games.Result) {
        val userEntity = games.mapDomainToEntity()
        localDataSource.deleteGames(userEntity)    }

    override fun readAllData(): Flow<Games> {
        return localDataSource.readAllData().map { it.mapEntitiesToDomains() }
    }
}