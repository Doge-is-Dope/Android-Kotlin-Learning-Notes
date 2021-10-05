package com.chunchiehliang.koinsample

import android.app.Application
import com.chunchiehliang.koinsample.di.appModule
import com.chunchiehliang.koinsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(listOf(appModule, viewModelModule))
        }
    }
}