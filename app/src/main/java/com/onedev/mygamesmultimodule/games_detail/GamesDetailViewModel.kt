package com.onedev.mygamesmultimodule.games_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onedev.data.model.Games
import com.onedev.domain.GameUseCase
import kotlinx.coroutines.launch

class GamesDetailViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun fetchDetailGames(id : Int) = gameUseCase.gamesDetail(id).asLiveData()

    fun insertGames(games: Games.Result) {
        viewModelScope.launch {
            gameUseCase.addGames(games)
        }
    }

    fun deleteGames(games: Games.Result) {
        viewModelScope.launch {
            gameUseCase.deleteGames(games)
        }
    }

    fun checkGameIsFavorite(id: Int) = gameUseCase.checkGameIsFavorite(id).asLiveData()

}