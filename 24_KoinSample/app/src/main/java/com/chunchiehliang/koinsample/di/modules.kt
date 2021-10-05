package com.chunchiehliang.koinsample.di

import com.chunchiehliang.koinsample.repository.UserRepository
import com.chunchiehliang.koinsample.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of UserRepository
    single { UserRepository() }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}