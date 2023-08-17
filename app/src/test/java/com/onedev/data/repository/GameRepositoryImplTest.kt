package com.onedev.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.onedev.local.LocalDataSource
import com.onedev.local.database.dao.GamesDao
import com.onedev.mygamesmultimodule.utils.DataDummy
import com.onedev.network.NetworkDataSource
import com.onedev.network.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameRepositoryImplTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var gamesDao: GamesDao

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var networkDataSource: NetworkDataSource

    private lateinit var gameRepositoryImpl: GameRepositoryImpl

    @Before
    fun setup() {
        networkDataSource = NetworkDataSource(apiService)
        localDataSource = LocalDataSource(gamesDao)
        gameRepositoryImpl = GameRepositoryImpl(networkDataSource, localDataSource)
    }

    @Test
    fun `when getGames() Should Not Null`() = runTest {
        val expectedGame = DataDummy.generateDummyGamesResponse()
        val actualGame = gameRepositoryImpl.games("").asLiveData()

        assertNotNull(expectedGame)
        assertNotNull(actualGame)
    }

}