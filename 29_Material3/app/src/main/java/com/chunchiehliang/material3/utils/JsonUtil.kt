package com.chunchiehliang.material3.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun parseStringToIntList(s: String): List<Int>? {
        val adapter: JsonAdapter<List<Int>> =
            moshi.adapter<List<Int>>(Types.newParameterizedType(
                List::class.java, Integer::class.java)).nonNull()
        return adapter.fromJson(s)
    }
}

