package com.chunchiehliang.material3

import android.app.Application
import com.chunchiehliang.material3.ui.filter.FilterViewModel
import com.google.android.material.color.DynamicColors
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import timber.log.Timber

class Material3App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@Material3App)
            modules(appModule)
        }
    }

    private val appModule = module {
        viewModel { FilterViewModel(get()) }
    }
}