package com.chunchiehliang.navigationsample.network

import com.chunchiehliang.navigationsample.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object apiClient {
    private const val BASE_URL = "https://api.unsplash.com/"

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("Accept", "application/json")
                builder.addHeader("Accept-Version", "v1")
//                builder.addHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}")
                return@Interceptor chain.proceed(builder.build())
            })
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    val unsplashService: UnsplashService by lazy {
        retrofit().create(UnsplashService::class.java)
    }
}