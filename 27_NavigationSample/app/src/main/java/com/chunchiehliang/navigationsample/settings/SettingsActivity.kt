package com.chunchiehliang.navigationsample.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunchiehliang.navigationsample.databinding.ActivityMainBinding
import com.chunchiehliang.navigationsample.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}