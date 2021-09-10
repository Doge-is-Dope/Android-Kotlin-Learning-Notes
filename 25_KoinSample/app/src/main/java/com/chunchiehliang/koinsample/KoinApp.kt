package com.chunchiehliang.koinsample

import android.app.Application
import timber.log.Timber

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}