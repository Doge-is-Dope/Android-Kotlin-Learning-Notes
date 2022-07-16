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


    /**
     * Get a list of [Book]s
     */
    suspend fun getBooks(): Result<List<Book>> = withContext(Dispatchers.IO) {
        try {
            delay(2000)
            Result.success(BOOKS)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get the selected [Book] IDs
     */
    suspend fun getSelectedBooks(): Result<List<Int>> = withContext(Dispatchers.IO) {
        try {
            val total = (1..2).random()
            val res = mutableListOf<Int>()
            for (count in 1..total) res.add((1..BOOKS.size).random())
            delay(3000)
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}