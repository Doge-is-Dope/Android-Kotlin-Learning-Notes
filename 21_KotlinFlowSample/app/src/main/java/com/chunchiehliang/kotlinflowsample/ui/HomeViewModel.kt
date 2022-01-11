package com.chunchiehliang.kotlinflowsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.kotlinflowsample.data.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _test = MutableLiveData<String>()
    val test: LiveData<String> get() = _test

    init {
        viewModelScope.launch {
            repo.getFacts()
        }
    }
}