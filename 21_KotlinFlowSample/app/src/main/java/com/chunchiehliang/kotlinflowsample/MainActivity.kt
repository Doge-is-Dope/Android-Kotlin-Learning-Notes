package com.chunchiehliang.kotlinflowsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunchiehliang.kotlinflowsample.ui.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeViewModel.test.observe(this, {
            Timber.d("test: $it")
        })
    }
}