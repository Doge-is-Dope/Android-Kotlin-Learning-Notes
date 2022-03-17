package com.chunchiehliang.sendbirdsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunchiehliang.sendbirdsample.databinding.ActivityChannelListBinding
import com.chunchiehliang.sendbirdsample.ui.adapter.ChannelAdapter
import com.chunchiehliang.sendbirdsample.ui.adapter.viewholder.ChannelViewHolder
import com.sendbird.android.GroupChannel
import timber.log.Timber

class ChannelListActivity : AppCompatActivity() {

    private val channelAdapter by lazy {
        ChannelAdapter(ChannelViewHolder.ChannelListener { Timber.d("clicked: ${it.url}") })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChannelListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            channelList.setHasFixedSize(true)
            channelList.adapter = channelAdapter

        }

        retrieveChannels()
    }

    private fun retrieveChannels() {
        val channelList = GroupChannel.createMyGroupChannelListQuery()
        channelList.limit = 100
        channelList.next { list, exeption ->
            if (exeption != null) Timber.e("Exception: ${exeption.message}")
            channelAdapter.submitList(list)
        }
    }
}