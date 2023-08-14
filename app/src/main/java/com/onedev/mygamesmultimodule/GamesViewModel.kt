package com.onedev.mygamesmultimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onedev.network.domain.GamesUseCase

class GamesViewModel(private val gamesUseCase: GamesUseCase): ViewModel() {
    fun fetchGames() = gamesUseCase.games().asLiveData()
}