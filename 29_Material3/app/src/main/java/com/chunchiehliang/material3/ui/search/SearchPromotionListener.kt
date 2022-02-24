package com.chunchiehliang.material3.ui.search

import com.chunchiehliang.material3.data.SearchPromotion

class SearchPromotionListener(val listener: (p: SearchPromotion) -> Unit) {
    fun onClick(p: SearchPromotion) = listener(p)
}