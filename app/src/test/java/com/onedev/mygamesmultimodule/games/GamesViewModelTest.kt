package com.onedev.mygamesmultimodule.games

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.onedev.data.model.Games
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GamesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gameUseCase: GameUseCase
    private lateinit var gamesViewModel: GamesViewModel
    private val dummyGames = DataDummy.generateDataDummyGame()

    @Before
    fun setup() {
        gamesViewModel = GamesViewModel(gameUseCase)
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
    fun `when get list games should not null and return success`() = runTest {
        val expectedGames = MutableLiveData<StateEvent<Games>>()
        expectedGames.value = StateEvent.Success(dummyGames)

        `when`(gameUseCase.games("")).thenReturn(expectedGames.asFlow())

        val actualGames = gamesViewModel.fetchGames("").getOrAwaitValue()
        verify(gameUseCase).games("")

        assertNotNull(actualGames)
        assertTrue(actualGames is StateEvent.Success)
        assertEquals(dummyGames.results?.size, (actualGames as StateEvent.Success).data.results?.size)
    }

    @Test
    fun `when get list game return response error`() {
        val expectedGames = MutableLiveData<StateEvent<Games>>()
        expectedGames.value = StateEvent.Error("Error")

        `when`(gameUseCase.games("")).thenReturn(expectedGames.asFlow())

        val actualGames = gamesViewModel.fetchGames("").getOrAwaitValue()
        verify(gameUseCase).games("")
        assertNotNull(actualGames)
        assertTrue(actualGames is StateEvent.Error)
    }
}
