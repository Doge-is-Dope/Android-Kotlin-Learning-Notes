package com.chunchiehliang.sendbirdsample.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.chunchiehliang.sendbirdsample.ui.adapter.viewholder.ChannelViewHolder
import com.sendbird.android.GroupChannel

class ChannelAdapter(private val listener: ChannelViewHolder.ChannelListener) :
    ListAdapter<GroupChannel, ChannelViewHolder>(ChannelDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        return ChannelViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }


    object ChannelDiffCallback : DiffUtil.ItemCallback<GroupChannel>() {
        override fun areItemsTheSame(oldItem: GroupChannel, newItem: GroupChannel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: GroupChannel, newItem: GroupChannel): Boolean {
            return oldItem == newItem
        }
    }


}