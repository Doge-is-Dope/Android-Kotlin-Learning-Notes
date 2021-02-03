package com.example.android.gdgfinder.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GdgChapterRegion(
    @Json(name = "id") val id: Long,
    @Json(name = "order") val order: Int,
    @Json(name = "title") val title: String,
    @Json(name = "chapters") val chapters: List<GdgChapter>
) : Parcelable


@Parcelize
data class GdgChapter(
    @Json(name = "active") val active: Boolean,
    @Json(name = "city") val city: String,
    @Json(name = "country") val country: String,
    @Json(name = "id") val id: Long,
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "state") val state: String,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String
) : Parcelable {
    val geo: LatLong = LatLong(latitude, longitude)
}

@Parcelize
data class LatLong(
    val lat: Double,
    @Json(name = "lng")
    val long: Double
) : Parcelable
