package com.chunchiehliang.material3.data

data class Size(
    val id: Int,
)

data class Condition(
    val id: Int,
)

enum class Sort { relevance, price_desc, price_asc, date_desc }