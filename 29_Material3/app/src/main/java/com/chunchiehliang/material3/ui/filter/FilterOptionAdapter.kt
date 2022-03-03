package com.chunchiehliang.material3.ui.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.data.FilterOption
import com.chunchiehliang.material3.databinding.ItemFilterOptionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class FilterOptionAdapter(private val listener: OptionListener) :
    ListAdapter<FilterOption, FilterOptionAdapter.OptionViewHolder>(OptionDiffCallback) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        getItem(position).let { option ->
            holder.bind(option, listener)
        }
    }

//    override fun submitList(list: List<FilterOption>?) {
//        adapterScope.launch {
//            val newList = list?.map { item -> item.copy() }
//            super.submitList(newList)
//        }
//    }


    class OptionViewHolder(private var binding: ItemFilterOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(option: FilterOption, optionListener: OptionListener) {
            binding.filterOption = option
            binding.tvValue.text = "${option.minPrice} ${option.maxPrice}"
            binding.listener = optionListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): OptionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFilterOptionBinding.inflate(layoutInflater, parent, false)
                return OptionViewHolder(binding)
            }
        }
    }
}


object OptionDiffCallback : DiffUtil.ItemCallback<FilterOption>() {
    override fun areItemsTheSame(oldItem: FilterOption, newItem: FilterOption): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilterOption, newItem: FilterOption): Boolean {
        return oldItem == newItem
    }
}

class OptionListener(val listener: (option: FilterOption) -> Unit) {
    fun onClick(option: FilterOption): Unit = listener(option)
}