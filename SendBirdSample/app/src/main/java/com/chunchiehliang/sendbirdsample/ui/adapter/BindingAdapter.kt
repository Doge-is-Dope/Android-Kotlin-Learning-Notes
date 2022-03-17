package com.chunchiehliang.sendbirdsample.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("avatarUrl")
fun ImageView.bindAvatarUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("lastUpdateAt")
fun TextView.bindTimeStamp(timestamp: Long){
    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    text = sdf.format(Date(timestamp))
}