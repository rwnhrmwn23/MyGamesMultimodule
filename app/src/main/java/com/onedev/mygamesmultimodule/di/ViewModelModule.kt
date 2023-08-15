package com.onedev.mygamesmultimodule.di

import com.onedev.mygamesmultimodule.games.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GamesViewModel(get()) }
}