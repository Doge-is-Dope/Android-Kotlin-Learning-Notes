package com.chunchiehliang.navigationsample.modal


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: Long,
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "avatar") val avatarUrl: String,
)