package com.chunchiehliang.kotlinflowsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.chunchiehliang.kotlinflowsample.ui.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            homeViewModel.parentList.collect {
                Timber.d("list: $it")
            }
        }

        lifecycleScope.launch {
            homeViewModel.selectedIdSet.collect {
                Timber.d("selected ids: $it")
            }
        }

        lifecycleScope.launch {
            homeViewModel.selectedLabel.collect {
                Timber.d("selected labels: $it")
            }
        }
    }
}