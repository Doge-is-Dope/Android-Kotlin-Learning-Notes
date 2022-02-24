package com.chunchiehliang.material3.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.data.SearchTerm
import com.chunchiehliang.material3.databinding.ItemSearchTermBinding

class SearchTermAdapter(private val listener: SearchTermListener) :
    ListAdapter<SearchTerm, SearchTermAdapter.SearchTermViewHolder>(
        SearchTermDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTermViewHolder {
        return SearchTermViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchTermViewHolder, position: Int) {
        getItem(position)?.let { term ->
            holder.bind(term)
        }
    }


    class SearchTermViewHolder(private var binding: ItemSearchTermBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(term: SearchTerm) {
            binding.searchTerm = term
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchTermViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSearchTermBinding.inflate(layoutInflater, parent, false)
                return SearchTermViewHolder(binding)
            }
        }
    }
}


class SearchTermDiffCallback : DiffUtil.ItemCallback<SearchTerm>() {
    override fun areItemsTheSame(oldItem: SearchTerm, newItem: SearchTerm): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchTerm, newItem: SearchTerm): Boolean {
        return oldItem == newItem
    }
}