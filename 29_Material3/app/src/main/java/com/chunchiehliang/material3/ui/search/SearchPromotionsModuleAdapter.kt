package com.chunchiehliang.material3.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.data.SearchPromotion
import com.chunchiehliang.material3.data.SearchPromotionsModule
import com.chunchiehliang.material3.databinding.ItemSearchPromotionBinding
import com.chunchiehliang.material3.databinding.ItemSearchPromotionsModuleBinding
import com.chunchiehliang.material3.utils.GridSpacingItemDecoration


class SearchPromotionsModuleAdapter(val listener: SearchPromotionListener) :
    ListAdapter<SearchPromotionsModule, SearchPromotionsModuleAdapter.PromotionsModuleViewHolder>(
        SearchPromotionModuleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionsModuleViewHolder {
        return PromotionsModuleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PromotionsModuleViewHolder, position: Int) {
        getItem(position)?.let { module ->
            holder.bind(module, listener)
        }
    }


    class PromotionsModuleViewHolder(private var binding: ItemSearchPromotionsModuleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: SearchPromotionsModule, listener: SearchPromotionListener) {
            binding.title = module.title
            val promotionAdapter = SearchPromotionAdapter(listener)
            binding.gridView.apply {
                setHasFixedSize(true)
                adapter = promotionAdapter
                addItemDecoration(GridSpacingItemDecoration(3,
                    context.resources.getDimensionPixelSize(R.dimen.material_margin),
                    true))
            }
            promotionAdapter.submitList(module.promotions)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PromotionsModuleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemSearchPromotionsModuleBinding.inflate(layoutInflater, parent, false)
                return PromotionsModuleViewHolder(binding)
            }
        }
    }
}


class SearchPromotionModuleDiffCallback : DiffUtil.ItemCallback<SearchPromotionsModule>() {
    override fun areItemsTheSame(
        oldItem: SearchPromotionsModule,
        newItem: SearchPromotionsModule,
    ): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: SearchPromotionsModule,
        newItem: SearchPromotionsModule,
    ): Boolean =
        oldItem == newItem
}