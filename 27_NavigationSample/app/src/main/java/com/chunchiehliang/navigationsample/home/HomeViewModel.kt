package com.chunchiehliang.navigationsample.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel() : ViewModel() {
    private val _navigateToProduct = MutableLiveData<Long?>()
    val navigateToProduct: LiveData<Long?> get() = _navigateToProduct

    private val _navigateToUser = MutableLiveData<Pair<String, String?>?>()
    val navigateToUser: LiveData<Pair<String, String?>?> get() = _navigateToUser

    fun onProductClick(productNo: Long) {
        _navigateToProduct.value = productNo
    }

    fun onNavigateToProductComplete() {
        _navigateToProduct.value = null
    }

    fun onUserClick() {
        _navigateToUser.value = Pair("mike_test", null)
    }

    fun onNavigateToUserComplete() {
        _navigateToUser.value = null
    }

}