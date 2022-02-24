package com.chunchiehliang.material3.ui.search

import com.chunchiehliang.material3.data.SearchTerm

class SearchTermListener(val listener: (term: SearchTerm) -> Unit) {
    fun onClick(term: SearchTerm) = listener(term)
}