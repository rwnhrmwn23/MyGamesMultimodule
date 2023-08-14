package com.onedev.mygamesmultimodule

import android.app.Application
import com.onedev.network.di.networkModule
import com.onedev.network.di.repositoryNetworkModule
import com.onedev.network.di.useCaseNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryNetworkModule,
                    useCaseNetworkModule,
                    viewModelModule,
                )
            )
        }
    }
}