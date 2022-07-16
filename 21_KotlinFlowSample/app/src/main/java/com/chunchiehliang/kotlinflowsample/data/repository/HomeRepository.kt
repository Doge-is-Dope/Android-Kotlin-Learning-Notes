package com.chunchiehliang.kotlinflowsample.data.repository

import com.chunchiehliang.kotlinflowsample.data.model.Book
import com.chunchiehliang.kotlinflowsample.data.model.Data
import com.chunchiehliang.kotlinflowsample.network.CatFactService
import com.chunchiehliang.kotlinflowsample.util.BOOKS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository(private val catFactService: CatFactService) {

    suspend fun getFacts(): Result<Data> = try {
        Result.success(catFactService.getFacts())
    } catch (e: Exception) {
        Result.failure(e)
    }


    suspend fun getBooks(): Result<List<Book>> = try {
        withContext(Dispatchers.IO) {
            delay(5000)
            Result.success(BOOKS)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}