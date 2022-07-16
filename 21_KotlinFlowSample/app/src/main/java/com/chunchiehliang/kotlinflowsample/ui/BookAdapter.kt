package com.chunchiehliang.kotlinflowsample.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.chunchiehliang.kotlinflowsample.data.model.Book

class BookAdapter(private val listener: BookViewHolder.Listener) :
    ListAdapter<Book, BookViewHolder>(BookDiffCallback) {

    object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder.from(parent)

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position).let { holder.bind(it, listener) }
    }
}


