package com.chunchiehliang.uikitsample

import android.app.Application
import com.chunchiehliang.uikitsample.sendbird.SendbirdUiKitUtils
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        SendbirdUiKitUtils.initSendbird(applicationContext)
    }
}