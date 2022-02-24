package com.chunchiehliang.material3.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.databinding.ItemSearchUsersModuleBinding


class SearchUsersModuleAdapter(val listener: SearchUsersModuleViewHolder.Listener) :
    ListAdapter<String, SearchUsersModuleAdapter.SearchUsersModuleViewHolder>(
        SearchUsersModuleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUsersModuleViewHolder {
        return SearchUsersModuleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchUsersModuleViewHolder, position: Int) {
        getItem(position)?.let { title ->
            holder.bind(title, listener)
        }
    }


    class SearchUsersModuleViewHolder(private var binding: ItemSearchUsersModuleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(title: String, navListener: Listener) {
            binding.listener = navListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchUsersModuleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemSearchUsersModuleBinding.inflate(layoutInflater, parent, false)
                return SearchUsersModuleViewHolder(binding)
            }
        }

        class Listener(val listener: () -> Unit) {
            fun onClick() = listener()
        }
    }
}


class SearchUsersModuleDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}