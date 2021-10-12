package com.chunchiehliang.navigationsample.modal


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "id") val id: String,
    @Json(name = "location") val location: Location,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "urls") val urls: Urls,
    @Json(name = "user") val user: User,
    @Json(name = "description") val description: String,
)