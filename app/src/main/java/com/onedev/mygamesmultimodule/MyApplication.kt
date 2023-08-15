package com.onedev.mygamesmultimodule

import android.app.Application
import com.onedev.di.databaseModule
import com.onedev.di.networkModule
import com.onedev.di.repositoryModule
import com.onedev.di.useCaseModule
import com.onedev.mygamesmultimodule.di.viewModelModule
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
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}