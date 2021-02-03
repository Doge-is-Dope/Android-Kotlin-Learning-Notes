package com.example.android.dessertpusher

import android.app.Application
import timber.log.Timber

class PusherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}