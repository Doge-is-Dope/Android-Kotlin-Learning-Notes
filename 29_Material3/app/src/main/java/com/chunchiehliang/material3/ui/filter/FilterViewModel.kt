package com.chunchiehliang.material3.ui.filter

import android.app.Application
import androidx.lifecycle.ViewModel
import com.chunchiehliang.material3.data.FilterOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

class FilterViewModel(val app: Application) : ViewModel() {

    private val options = listOf(
        FilterOption(0, "Category"),
        FilterOption(1, "Price"),
    )

    private val _priceRange = MutableStateFlow<Pair<Float, Float>?>(null)
    val priceRange: StateFlow<Pair<Float, Float>?> get() = _priceRange

    private val _optionsFlow = MutableStateFlow(emptyList<FilterOption>())
    val optionsFlow: StateFlow<List<FilterOption>> = _optionsFlow

    val combinedFlow: Flow<List<FilterOption>> =
        optionsFlow.combine(priceRange) { origin, range ->
            origin.map { option ->
                range?.let {
                    if (option.id == 1) {
                        option.minPrice = range.first
                        option.maxPrice = range.second
                    }
                }
            }
            Timber.d("new options: $origin")
            origin
        }.flowOn(Dispatchers.Default)

    init {
        _optionsFlow.value = options
    }

    fun updatePriceRange(range: Pair<Float, Float>) {
        _priceRange.value = range
    }

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
    }
}