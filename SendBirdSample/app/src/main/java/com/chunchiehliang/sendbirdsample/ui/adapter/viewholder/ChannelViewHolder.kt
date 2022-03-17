package com.chunchiehliang.sendbirdsample.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.sendbirdsample.databinding.ListItemChannelBinding
import com.sendbird.android.GroupChannel

class ChannelViewHolder private constructor(private val binding: ListItemChannelBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: GroupChannel, listener: ChannelListener) {
        binding.channel = item
        binding.listener = listener
        binding.executePendingBindings()
    }

    class ChannelListener(val clickListener: (item: GroupChannel) -> Unit) {
        fun onClick(item: GroupChannel) = clickListener(item)
    }

    companion object {
        fun from(parent: ViewGroup): ChannelViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemChannelBinding.inflate(layoutInflater, parent, false)
            return ChannelViewHolder(binding)
        }
    }
}