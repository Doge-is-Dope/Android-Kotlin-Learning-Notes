package com.chunchiehliang.material3.ui.sort

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.data.SortOption
import com.chunchiehliang.material3.databinding.ItemSortBinding

class SortOptionAdapter(private val listener: OptionListener) :
    ListAdapter<SortOption, SortOptionAdapter.OptionViewHolder>(OptionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        getItem(position)?.let { option ->
            holder.bind(option, listener)
        }
    }


    class OptionViewHolder(private var binding: ItemSortBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(option: SortOption, optionListener: OptionListener) {
            binding.sortOption = option
            binding.listener = optionListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): OptionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSortBinding.inflate(layoutInflater, parent, false)
                return OptionViewHolder(binding)
            }
        }
    }
}


class OptionDiffCallback : DiffUtil.ItemCallback<SortOption>() {
    override fun areItemsTheSame(oldItem: SortOption, newItem: SortOption): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SortOption, newItem: SortOption): Boolean {
        return oldItem == newItem
    }
}

class OptionListener(val listener: (option: SortOption) -> Unit) {
    fun onClick(option: SortOption) = listener(option)
}