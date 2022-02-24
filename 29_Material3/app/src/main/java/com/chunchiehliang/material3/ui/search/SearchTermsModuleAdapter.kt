package com.chunchiehliang.material3.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.material3.R
import com.chunchiehliang.material3.data.SearchTermsModule
import com.chunchiehliang.material3.databinding.ItemSearchTermsModuleBinding
import com.google.android.flexbox.*


class SearchTermsModuleAdapter(val listener: SearchTermListener) :
    ListAdapter<SearchTermsModule, SearchTermsModuleAdapter.SearchTermModuleViewHolder>(
        SearchTermModuleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTermModuleViewHolder {
        return SearchTermModuleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchTermModuleViewHolder, position: Int) {
        getItem(position)?.let { module ->
            holder.bind(module, listener)
        }
    }


    class SearchTermModuleViewHolder(private var binding: ItemSearchTermsModuleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: SearchTermsModule, listener: SearchTermListener) {
            binding.title = module.title
            val searchTermAdapter = SearchTermAdapter(listener)
            binding.rvChipGroup.apply {
                val flexBoxLayoutManager = FlexboxLayoutManager(context).apply {
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                    alignItems = AlignItems.FLEX_START
                    flexWrap = FlexWrap.WRAP

                }
                val itemDecoration = FlexboxItemDecoration(context).apply {
                    setOrientation(FlexboxItemDecoration.BOTH)
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_search_term))
                }
                if (itemDecorationCount == 0) addItemDecoration(itemDecoration)
                layoutManager = flexBoxLayoutManager
                adapter = searchTermAdapter
            }
            searchTermAdapter.submitList(module.terms)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchTermModuleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSearchTermsModuleBinding.inflate(layoutInflater, parent, false)
                return SearchTermModuleViewHolder(binding)
            }
        }
    }
}


class SearchTermModuleDiffCallback : DiffUtil.ItemCallback<SearchTermsModule>() {
    override fun areItemsTheSame(oldItem: SearchTermsModule, newItem: SearchTermsModule): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: SearchTermsModule,
        newItem: SearchTermsModule,
    ): Boolean =
        oldItem == newItem
}