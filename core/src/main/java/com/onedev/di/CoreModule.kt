package com.onedev.di

import androidx.room.Room
import com.onedev.data.repository.GameRepository
import com.onedev.data.repository.GameRepositoryImpl
import com.onedev.domain.GameUseCaseImpl
import com.onedev.domain.GameUseCase
import com.onedev.local.database.GameDatabase
import com.onedev.local.LocalDataSource
import com.onedev.network.BuildConfig
import com.onedev.network.service.ApiService
import com.onedev.network.NetworkDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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

val databaseModule = module {
    factory { get<GameDatabase>().gamesDao() }
    single {
        Room.databaseBuilder(androidContext(), GameDatabase::class.java, "db_games")
            .fallbackToDestructiveMigration()
            .build()
    }
}

val useCaseModule = module {
    factory<GameUseCase> {
        GameUseCaseImpl(get())
    }
}

val repositoryModule = module {
    single { NetworkDataSource(get()) }
    single { LocalDataSource(get()) }
    single<GameRepository> {
        GameRepositoryImpl(get(), get())
    }
}