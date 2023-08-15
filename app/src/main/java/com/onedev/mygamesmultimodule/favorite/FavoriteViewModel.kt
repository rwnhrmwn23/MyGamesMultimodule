package com.onedev.mygamesmultimodule.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.onedev.domain.GameUseCase

class FavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun fetchFavoriteGames() = gameUseCase.readAllData().asLiveData()
}