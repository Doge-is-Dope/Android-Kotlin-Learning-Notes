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
            delay(500)
            val total = (1..5).random()
            if (total % 2 == 0) throw Exception("Total selected cannot be $total")
            val res = List(total) { ((1..Int.MAX_VALUE).random() % 5) + 1 }
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}