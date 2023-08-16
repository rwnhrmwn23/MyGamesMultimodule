package com.onedev.mygamesmultimodule.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.onedev.data.model.Games
import com.onedev.domain.GameUseCase
import com.onedev.mygamesmultimodule.utils.DataDummy
import com.onedev.mygamesmultimodule.utils.getOrAwaitValue
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
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gameUseCase: GameUseCase
    private lateinit var favoriteViewModel: FavoriteViewModel
    private val dummyGames = DataDummy.generateDataDummyGame()
    private val nullDummyGames = DataDummy.generateNullDataDummyGame()

    @Before
    fun setup() {
        favoriteViewModel = FavoriteViewModel(gameUseCase)
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
    fun `when get list favorite games should not null and return success`() = runTest {
        val expectedFavoriteGames = MutableLiveData<Games>()
        expectedFavoriteGames.value = dummyGames

        `when`(gameUseCase.readAllData()).thenReturn(expectedFavoriteGames.asFlow())

        val actualFavoriteGames = favoriteViewModel.fetchFavoriteGames().getOrAwaitValue()
        verify(gameUseCase).readAllData()

        assertNotNull(actualFavoriteGames)
        assertTrue(actualFavoriteGames.results?.isNotEmpty() == true)
        assertEquals(dummyGames.results?.size, (actualFavoriteGames).results?.size)
    }

    @Test
    fun `when get list favorite game and data null`() {
        val expectedFavoriteGames = MutableLiveData<Games>()
        expectedFavoriteGames.value = nullDummyGames

        `when`(gameUseCase.readAllData()).thenReturn(expectedFavoriteGames.asFlow())

        val actualFavoriteGames = favoriteViewModel.fetchFavoriteGames().getOrAwaitValue()
        verify(gameUseCase).readAllData()

        assertNotNull(actualFavoriteGames)
        assertTrue(actualFavoriteGames.results?.isNotEmpty() != true)
    }
}