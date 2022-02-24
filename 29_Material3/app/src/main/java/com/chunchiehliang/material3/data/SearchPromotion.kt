package com.chunchiehliang.material3.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPromotion(
    @Json(name = "id") val id: Long,
    @Json(name = "image") val imageUrl: String,
    @Json(name = "text") val title: String,

    @Json(name = "web_link") val webLink: String?,
    @Json(name = "app_link") val appLink: String?,
)
