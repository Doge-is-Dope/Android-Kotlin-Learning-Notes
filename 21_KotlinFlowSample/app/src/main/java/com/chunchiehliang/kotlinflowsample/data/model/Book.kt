package com.chunchiehliang.kotlinflowsample.data.model

data class Book(val id: Int, val title: String, var isSelected: Boolean = false) {
    val idDisplay: String = id.toString()
}
