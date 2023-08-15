package com.onedev.mygamesmultimodule.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onedev.domain.GameUseCase

class GamesViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun fetchGames(search : String) = gameUseCase.games(search).asLiveData()
}