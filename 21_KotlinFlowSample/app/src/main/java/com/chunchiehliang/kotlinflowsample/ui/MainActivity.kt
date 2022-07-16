package com.chunchiehliang.kotlinflowsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.chunchiehliang.kotlinflowsample.data.model.Book
import com.chunchiehliang.kotlinflowsample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityMainBinding

    private val bookAdapter = BookAdapter(BookViewHolder.Listener {
        showMessage("Clicked: ${it.title}")
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bookList.apply {
                adapter = bookAdapter
                setHasFixedSize(true)
            }

            btnRetry.setOnClickListener { homeViewModel.getSelectedBooks() }
        }

        homeViewModel.getSelectedBooks()

        lifecycleScope.launch {
            homeViewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> showLoadingLayout()
                        is UiState.Success -> showSuccessLayout(uiState.books)
                        is UiState.Error -> showErrorLayout(uiState.exception)
                    }
                }
        }
    }

    private fun showErrorLayout(error: Throwable) {
        binding.progress.isVisible = false
        binding.btnRetry.isVisible = true
        showMessage(error.message ?: "Unexpected error")
    }

    private fun showLoadingLayout() {
        binding.progress.isVisible = true
        binding.btnRetry.isVisible = false
    }

    private fun showSuccessLayout(books: List<Book>) {
        binding.progress.isVisible = true
        binding.btnRetry.isVisible = false
        binding.progress.isVisible = false
        bookAdapter.submitList(books)
    }

    private fun showMessage(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .show()
}