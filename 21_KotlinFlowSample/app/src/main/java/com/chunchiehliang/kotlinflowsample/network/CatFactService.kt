package com.chunchiehliang.kotlinflowsample.network

import com.chunchiehliang.kotlinflowsample.data.api.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactService {
    @GET("facts")
    suspend fun getFacts(
        @Query("limit") limit: Int = 1,
    ): Data
}