package com.chunchiehliang.kotlinflowsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.chunchiehliang.kotlinflowsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookAdapter = BookAdapter(BookViewHolder.Listener {
            Timber.d("Clicked: $it")
        })

        binding.bookList.apply {
            adapter = bookAdapter
            setHasFixedSize(true)
        }

        homeViewModel.fetchBookList()
        homeViewModel.fetchSelectedBooks()

        lifecycleScope.launch {
            homeViewModel.uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> binding.progress.isVisible = true
                        is UiState.Success -> {
                            binding.progress.isVisible = false
                            bookAdapter.submitList(uiState.books)
                        }
                        is UiState.Error -> {
                            binding.progress.isVisible = false
                            showErrorMessage(uiState.exception)
                        }
                    }
                }
        }

//        lifecycleScope.launch {
//            homeViewModel.selectedIdSet.collect {
//                Timber.d("selected ids: $it")
//            }
//        }
    }

    private fun showErrorMessage(error: Throwable) {
        Snackbar.make(binding.root, error.message ?: "Unexpected error", Snackbar.LENGTH_SHORT)
            .show()
    }
}