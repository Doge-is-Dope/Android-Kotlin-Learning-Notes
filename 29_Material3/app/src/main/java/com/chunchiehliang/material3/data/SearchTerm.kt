package com.chunchiehliang.material3.data

data class SearchTerm(
    val id: Long,
    val title: String,
    val isHighlighted: Boolean? = false,
)
