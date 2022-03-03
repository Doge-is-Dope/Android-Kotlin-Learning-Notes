package com.chunchiehliang.material3.data

data class FilterOption(
    val id: Int,
    val title: String,
    var category: String? = null,
    var minPrice: Float? = 0F,
    var maxPrice: Float? = 20000F,
)
