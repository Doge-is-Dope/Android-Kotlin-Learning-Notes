package com.chunchiehliang.material3.ui.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel : ViewModel() {
    private val _badgeCount = MutableStateFlow(0)
    val badgeCount get() = _badgeCount


    fun increment() {
        _badgeCount.value = _badgeCount.value + 1
    }

    fun decrement() {
        _badgeCount.value = _badgeCount.value - 1
    }
}