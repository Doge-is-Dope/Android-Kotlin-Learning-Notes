package com.chunchiehliang.material3

import android.app.Application
import timber.log.Timber

class Material3App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}