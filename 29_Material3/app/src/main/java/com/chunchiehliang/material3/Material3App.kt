package com.chunchiehliang.material3

import android.app.Application
import com.google.android.material.color.DynamicColors
import timber.log.Timber

class Material3App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}