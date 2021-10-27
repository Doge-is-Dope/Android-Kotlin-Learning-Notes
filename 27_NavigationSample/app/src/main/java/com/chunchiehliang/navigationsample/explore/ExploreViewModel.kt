package com.chunchiehliang.navigationsample.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.navigationsample.modal.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExploreViewModel : ViewModel() {

    private val _productNo = MutableLiveData<Long?>()
    val productNo: LiveData<Long?> get() = _productNo

    fun setProductNo(no: Long) {
        _productNo.value = no
    }

}