package com.chunchiehliang.sendbirdsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunchiehliang.sendbirdsample.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}