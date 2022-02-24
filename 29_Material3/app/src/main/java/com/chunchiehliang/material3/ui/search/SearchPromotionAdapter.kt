package com.chunchiehliang.material3.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.data.SearchPromotion
import com.chunchiehliang.material3.databinding.ItemSearchPromotionBinding

class SearchPromotionAdapter(private val listener: SearchPromotionListener) :
    ListAdapter<SearchPromotion, SearchPromotionAdapter.PromotionViewHolder>(
        PromotionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        return PromotionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        getItem(position)?.let { term ->
            holder.bind(term, listener)
        }
    }


    class PromotionViewHolder(private var binding: ItemSearchPromotionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchPromotion: SearchPromotion, promotionListener: SearchPromotionListener) {
            binding.promotion = searchPromotion
            binding.listener = promotionListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PromotionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSearchPromotionBinding.inflate(layoutInflater, parent, false)
                return PromotionViewHolder(binding)
            }
        }
    }
}


class PromotionDiffCallback : DiffUtil.ItemCallback<SearchPromotion>() {
    override fun areItemsTheSame(oldItem: SearchPromotion, newItem: SearchPromotion): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchPromotion, newItem: SearchPromotion): Boolean {
        return oldItem == newItem
    }
}