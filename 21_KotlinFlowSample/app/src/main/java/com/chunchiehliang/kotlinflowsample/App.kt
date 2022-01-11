package com.chunchiehliang.kotlinflowsample

import android.app.Application
import com.chunchiehliang.kotlinflowsample.data.repository.HomeRepository
import com.chunchiehliang.kotlinflowsample.network.CatFactApi
import com.chunchiehliang.kotlinflowsample.ui.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

import timber.log.Timber.*


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }


        val appModule = module {
            single { HomeRepository(CatFactApi.catFactService) }
            viewModel { HomeViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}