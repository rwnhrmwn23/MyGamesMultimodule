package com.onedev.mygamesmultimodule.games_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.onedev.data.model.Games
import com.onedev.data.model.GamesDetail
import com.onedev.domain.GameUseCase
import com.onedev.mygamesmultimodule.utils.DataDummy
import com.onedev.mygamesmultimodule.utils.getOrAwaitValue
import com.onedev.utils.StateEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class GamesDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gameUseCase: GameUseCase
    private lateinit var gamesDetailViewModel: GamesDetailViewModel
    private val dummyGames = DataDummy.generateDataDummyGame().results?.get(0)
    private val dummyGamesResult = DataDummy.generateDataResultDummyGame()
    private val dummyGamesResultNull = DataDummy.generateNullDataDetailDummyGame()
    private val dummyGamesDetail = DataDummy.generateDetailDummyGame()

    @Before
    fun setup() {
        gamesDetailViewModel = GamesDetailViewModel(gameUseCase)
    }

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when get detail games should not null and return success`() = runTest {
        val expectedDetailGame = MutableLiveData<StateEvent<GamesDetail>>()
        expectedDetailGame.value = StateEvent.Success(dummyGamesDetail)

        `when`(gameUseCase.gamesDetail(1)).thenReturn(expectedDetailGame.asFlow())

        val actualDetailGames = gamesDetailViewModel.fetchDetailGames(1).getOrAwaitValue()
        verify(gameUseCase).gamesDetail(1)

        assertNotNull(actualDetailGames)
        assertTrue(actualDetailGames is StateEvent.Success)
        assertEquals(dummyGamesDetail.id, (dummyGamesDetail).id)
    }

    @Test
    fun `when get detail games return response error`() = runTest {
        val expectedDetailGame = MutableLiveData<StateEvent<GamesDetail>>()
        expectedDetailGame.value = StateEvent.Error("Error")

        `when`(gameUseCase.gamesDetail(1)).thenReturn(expectedDetailGame.asFlow())

        val actualDetailGames = gamesDetailViewModel.fetchDetailGames(1).getOrAwaitValue()
        verify(gameUseCase).gamesDetail(1)

        assertNotNull(actualDetailGames)
        assertTrue(actualDetailGames is StateEvent.Error)
        assertEquals(dummyGamesDetail.id, (dummyGamesDetail).id)
    }

    @Test
    fun `when favorite status false with gameResult data is null Should call insertGames()`() = runTest {
        val gameResult = MutableLiveData<Games.Result>()
        gameResult.value = Games.Result(id = 0)

        `when`(gameUseCase.checkGameIsFavorite(dummyGames?.id ?: 0)).thenReturn(gameResult.asFlow())
        dummyGames?.let { gamesDetailViewModel.insertGames(it) }
    }

    @Test
    fun `when favorite status true with gameResult data not null Should call deleteGames()`() = runTest {
        val gameResult = MutableLiveData<Games.Result>()
        gameResult.value = dummyGames

        `when`(gameUseCase.checkGameIsFavorite(dummyGames?.id ?: 0)).thenReturn(gameResult.asFlow())
        dummyGames?.let { gamesDetailViewModel.deleteGames(it) }
    }

    @Test
    fun `when checkGameIsFavorite return success data not null`() = runTest {
        val expectedGamesDetail = MutableLiveData<Games.Result>()
        expectedGamesDetail.value = dummyGamesResult

        `when`(gameUseCase.checkGameIsFavorite(1)).thenReturn(expectedGamesDetail.asFlow())

        val actualGames = gamesDetailViewModel.checkGameIsFavorite(1).getOrAwaitValue()
        verify(gameUseCase).checkGameIsFavorite(1)

        assertNotNull(actualGames)
        assertTrue(actualGames.id != null)
        assertEquals(dummyGamesResult.id, (actualGames).id)
    }

    @Test
    fun `when checkGameIsFavorite return false data is null`() = runTest {
        val expectedGamesDetail = MutableLiveData<Games.Result>()
        expectedGamesDetail.value = dummyGamesResultNull

        `when`(gameUseCase.checkGameIsFavorite(1)).thenReturn(expectedGamesDetail.asFlow())

        val actualGames = gamesDetailViewModel.checkGameIsFavorite(1).getOrAwaitValue()
        verify(gameUseCase).checkGameIsFavorite(1)

        assertNotNull(actualGames)
        assertTrue(actualGames.id == null)
        assertEquals(dummyGamesResultNull.id, (actualGames).id)
    }
}