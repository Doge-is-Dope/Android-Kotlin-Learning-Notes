package com.chunchiehliang.kotlinflowsample.data.repository

import com.chunchiehliang.kotlinflowsample.network.CatFactService
import timber.log.Timber

class HomeRepository(private val catFactService: CatFactService) {

    suspend fun getFacts() {
        try {
            val result = catFactService.getFacts()
            Timber.d("cat facts: $result")
        } catch (e: Exception) {
            Timber.e("Error: $e")
        }
    }
}