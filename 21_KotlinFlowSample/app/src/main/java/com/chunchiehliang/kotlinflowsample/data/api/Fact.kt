package com.chunchiehliang.kotlinflowsample.data.api

import com.squareup.moshi.Json

data class Fact(
    @Json(name = "fact") val content: String,
    @Json(name = "length") val length: Int,
)

data class Data(
    @Json(name = "data") val data: List<Fact>,
)
