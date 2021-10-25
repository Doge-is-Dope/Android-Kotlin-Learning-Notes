package com.chunchiehliang.navigationsample.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunchiehliang.navigationsample.modal.User
import com.chunchiehliang.navigationsample.repository.UnsplashRepository

class HomeViewModel() : ViewModel() {
    private val _navigateToProduct = MutableLiveData<Long?>()
    val navigateToProduct: LiveData<Long?> get() = _navigateToProduct

    private val _navigateToUser = MutableLiveData<Long?>()
    val navigateToUser: LiveData<Long?> get() = _navigateToUser

    fun onProductClick(productNo: Long) {
        _navigateToProduct.value = productNo
    }

    fun onNavigateToProductComplete() {
        _navigateToProduct.value = null
    }

    fun onUserClick(userId: Long) {
        _navigateToUser.value = userId
    }

    fun onNavigateToUserComplete() {
        _navigateToUser.value = null
    }

}