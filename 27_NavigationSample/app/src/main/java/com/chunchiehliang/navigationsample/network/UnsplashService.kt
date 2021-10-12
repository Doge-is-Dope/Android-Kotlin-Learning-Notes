package com.chunchiehliang.navigationsample.network

import com.chunchiehliang.navigationsample.modal.Photo
import retrofit2.http.GET

/**
 * Used to connect to the PopChill API
 */
interface UnsplashService {
    @GET("photos/random")
    suspend fun getARandom(): Photo
}


