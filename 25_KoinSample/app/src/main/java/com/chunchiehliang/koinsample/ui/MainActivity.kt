package com.chunchiehliang.koinsample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.koinsample.adapter.UserAdapter
import com.chunchiehliang.koinsample.databinding.ActivityMainBinding
import com.chunchiehliang.koinsample.repository.UserRepository
import com.chunchiehliang.koinsample.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val adapter by lazy { UserAdapter() }

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.userList.observe(this, {
            it?.let { adapter.submitList(it) }
        })


        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
            userList.adapter = adapter
        }
    }
}