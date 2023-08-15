package com.onedev.mygamesmultimodule.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onedev.data.model.Games
import com.onedev.domain.GamesUseCase
import kotlinx.coroutines.launch

class GamesViewModel(private val gamesUseCase: GamesUseCase): ViewModel() {
    fun fetchGames(search : String) = gamesUseCase.games(search).asLiveData()

    fun insertGames(games: Games.Result) {
        viewModelScope.launch {
            gamesUseCase.addGames(games)
        }
    }
}