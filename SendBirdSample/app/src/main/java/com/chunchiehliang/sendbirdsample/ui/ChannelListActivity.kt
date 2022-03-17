package com.chunchiehliang.sendbirdsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunchiehliang.sendbirdsample.databinding.ActivityChannelListBinding

class ChannelListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChannelListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}