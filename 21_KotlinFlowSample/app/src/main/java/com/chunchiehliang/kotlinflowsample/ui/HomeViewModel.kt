package com.chunchiehliang.kotlinflowsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.kotlinflowsample.data.model.Book
import com.chunchiehliang.kotlinflowsample.data.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            repo.getFacts()
        }
    }


    fun getSelectedBooks() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            combineTransform(fetchBooks(), fetchSelected()) { books, selected ->
                books.forEach { book -> book.isSelected = book.id in selected }
                emit(books)
            }
                .flowOn(Dispatchers.Default)
                .catch { _uiState.value = UiState.Error(it) }
                .collectLatest {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    // Simulate fetching books from API
    private fun fetchBooks(): Flow<List<Book>> = flow {
        repo.getBooks()
            .onSuccess { books -> emit(books) }
            .onFailure { throw it }
    }

    private fun fetchSelected(): Flow<List<Int>> = flow {
        repo.getSelectedBooks()
            .onSuccess { selected -> emit(selected) }
            .onFailure { throw it }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val books: List<Book>) : UiState()
    data class Error(val exception: Throwable) : UiState()
}