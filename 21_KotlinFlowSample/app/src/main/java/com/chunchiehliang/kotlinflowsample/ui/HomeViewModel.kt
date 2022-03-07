package com.chunchiehliang.kotlinflowsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunchiehliang.kotlinflowsample.data.model.Parent
import com.chunchiehliang.kotlinflowsample.data.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _parentList = MutableStateFlow<List<Parent>>(emptyList())
    val parentList get() = _parentList.asStateFlow()

    private val _selectedIdSet = MutableStateFlow<Set<Int>>(setOf())
    val selectedIdSet get() = _selectedIdSet.asStateFlow()

    val selectedLabel: Flow<List<String>> = parentList.combine(selectedIdSet) { list, set ->
        list.filter { parent -> parent.id in set }.map { it.title }
    }.flowOn(Dispatchers.Default)

    init {
        viewModelScope.launch {
            repo.getFacts()
        }

        viewModelScope.launch(Dispatchers.Default) {
            delay(3000L)
            _parentList.value = listOf(Parent(0, "AAA"), Parent(1, "BBB"), Parent(2, "CCC"))
        }

        viewModelScope.launch(Dispatchers.Default) {
            delay(100L)
            _selectedIdSet.value = setOf(1, 2)
        }
    }
}