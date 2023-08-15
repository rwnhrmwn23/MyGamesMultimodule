package com.onedev.mygamesmultimodule.di

import com.onedev.mygamesmultimodule.favorite.FavoriteViewModel
import com.onedev.mygamesmultimodule.games.GamesViewModel
import com.onedev.mygamesmultimodule.games_detail.GamesDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GamesViewModel(get()) }
    viewModel { GamesDetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}