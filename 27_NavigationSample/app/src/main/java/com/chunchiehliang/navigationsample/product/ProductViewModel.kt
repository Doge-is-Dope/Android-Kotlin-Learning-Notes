package com.chunchiehliang.navigationsample.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.navigationsample.modal.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel : ViewModel() {

    private val _navigateToUser = MutableLiveData<Long?>()
    val navigateToUser: LiveData<Long?> get() = _navigateToUser

    fun onUserClick(userId: Long) {
        _navigateToUser.value = userId
    }

    fun onNavigateToUserComplete() {
        _navigateToUser.value = null
    }

}