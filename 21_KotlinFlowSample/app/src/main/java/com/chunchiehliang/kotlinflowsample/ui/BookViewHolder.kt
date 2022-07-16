package com.chunchiehliang.kotlinflowsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlinflowsample.data.model.Book
import com.chunchiehliang.kotlinflowsample.databinding.ListItemBookBinding

class BookViewHolder(private val binding: ListItemBookBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(bookItem: Book, bookListener: Listener) {
        binding.apply {
            book = bookItem
            listener = bookListener
        }
    }

    companion object {
        fun from(parent: ViewGroup): BookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBookBinding.inflate(layoutInflater, parent, false)
            return BookViewHolder(binding)
        }
    }


    class Listener(val clickListener: (book: Book) -> Unit) {
        fun onClick(book: Book) = clickListener(book)
    }
}