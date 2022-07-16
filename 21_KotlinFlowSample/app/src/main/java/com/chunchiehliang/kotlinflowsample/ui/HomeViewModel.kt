package com.chunchiehliang.kotlinflowsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.kotlinflowsample.data.model.Book
import com.chunchiehliang.kotlinflowsample.data.repository.HomeRepository
import com.chunchiehliang.kotlinflowsample.util.BOOKS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState

    private val _selectedIdSet = MutableStateFlow<Set<Int>>(setOf())
    val selectedIdSet get() = _selectedIdSet.asStateFlow()

//    val selectedBooks: Flow<List<Book>> = bookList.combine(selectedIdSet) { list, set ->
//        list.filter { book -> book.id in set }
//    }.flowOn(Dispatchers.Default)

    init {
        viewModelScope.launch {
            repo.getFacts()
        }
    }

    // Simulate fetching books from API
    fun fetchBookList() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repo.getBooks().onSuccess {
                _uiState.value = UiState.Success(BOOKS)
            }.onFailure {
                _uiState.value = UiState.Error(it)
            }
        }
    }

    // Simulate fetching selected Ids from API (this shall return earlier than books)
    fun fetchSelectedBooks() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000L)
            _selectedIdSet.value = setOf(0, 2)
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val books: List<Book>) : UiState()
    data class Error(val exception: Throwable) : UiState()
}