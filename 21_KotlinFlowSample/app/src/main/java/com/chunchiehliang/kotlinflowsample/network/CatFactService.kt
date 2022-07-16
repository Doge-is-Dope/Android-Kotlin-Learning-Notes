package com.chunchiehliang.kotlinflowsample.network

import com.chunchiehliang.kotlinflowsample.data.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactService {
    @GET("facts")
    suspend fun getFacts(@Query("limit") limit: Int = 10): Data
}