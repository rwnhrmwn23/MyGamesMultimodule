package com.onedev.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.onedev.local.database.dao.GamesDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: GameDatabase
    private lateinit var dao: GamesDao
    private val sampleGames = DataDummy.generateDataEntitiesDummyGame()[0]

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GameDatabase::class.java
        ).build()
        dao = database.gamesDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun addGames_Success() = runTest {
        dao.addGames(sampleGames)
        val actualGames = dao.readAllGame().first()
        Assert.assertEquals(sampleGames.gamesId, actualGames[0].gamesId)
        Assert.assertTrue(dao.checkGameIsFavorite(sampleGames.gamesId).first().gamesId != 0)
    }

    @Test
    fun deleteGames_Success() = runTest {
        dao.addGames(sampleGames)
        dao.deleteGames(sampleGames)
        val actualGames = dao.readAllGame().first()
        Assert.assertTrue(actualGames.isEmpty())
        Assert.assertNull(dao.checkGameIsFavorite(sampleGames.gamesId).first())
    }
}