package com.chunchiehliang.koinsample.viewmodel

import androidx.lifecycle.*
import com.chunchiehliang.koinsample.modal.User
import com.chunchiehliang.koinsample.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> get() = _isLoading

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    init {
        _isLoading.value = true
        viewModelScope.launch {
            _userList.value = repository.getUserList()
            _isLoading.value = null
        }
    }


    class Factory(private val repository: UserRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}