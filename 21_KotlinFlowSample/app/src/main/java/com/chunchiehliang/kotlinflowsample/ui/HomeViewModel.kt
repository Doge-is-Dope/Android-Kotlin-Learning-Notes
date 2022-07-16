package com.chunchiehliang.kotlinflowsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.kotlinflowsample.data.model.Book
import com.chunchiehliang.kotlinflowsample.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            repo.getFacts()
        }
    }

    // Simulate fetching books from API
    fun fetchBookList() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repo.getBooks().onSuccess { books ->
                _uiState.value = UiState.Success(books)
            }.onFailure {
                _uiState.value = UiState.Error(it)
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val books: List<Book>) : UiState()
    data class Error(val exception: Throwable) : UiState()
}