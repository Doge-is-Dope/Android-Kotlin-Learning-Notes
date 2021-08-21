package com.chunchiehliang.roomsample

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("wordList")
fun RecyclerView.bindWordList(wordList: List<Word>?) {
    val adapter = adapter as WordListAdapter
    wordList?.let {
        adapter.submitList(wordList)
    }
}