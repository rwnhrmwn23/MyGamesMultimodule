package com.onedev.network.di

import com.onedev.network.BuildConfig
import com.onedev.network.data.repository.GameRepository
import com.onedev.network.data.repository.GameRepositoryImpl
import com.onedev.network.domain.GameUseCaseImpl
import com.onedev.network.domain.GamesUseCase
import com.onedev.network.service.ApiService
import com.onedev.network.source.NetworkDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        client.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryNetworkModule = module {
    single { NetworkDataSource(get()) }
    single<GameRepository> {
        GameRepositoryImpl(get())
    }
}

val useCaseNetworkModule = module {
    factory<GamesUseCase> {
        GameUseCaseImpl(get())
    }
}