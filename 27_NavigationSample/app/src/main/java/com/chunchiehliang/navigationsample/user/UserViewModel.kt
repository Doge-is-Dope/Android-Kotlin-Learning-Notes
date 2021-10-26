package com.chunchiehliang.navigationsample.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.navigationsample.modal.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> get() = _userId

    fun setUserId(userId: Int) {
        _userId.value = userId
    }
}