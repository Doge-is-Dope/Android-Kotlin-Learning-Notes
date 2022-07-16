package com.chunchiehliang.kotlinflowsample.data.model

data class Book(val id: Int, val title: String) {
    val idDisplay: String = id.toString()
}
