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

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    init {
        viewModelScope.launch {
            delay(2000L)
            withContext(Dispatchers.Default) {
                _user.postValue(User(1, "mike_test", "Mike", "https://source.unsplash.com/random"))
            }
        }
    }
}