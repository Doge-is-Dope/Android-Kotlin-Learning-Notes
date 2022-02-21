package com.chunchiehliang.material3.ui

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.chunchiehliang.material3.R


@BindingAdapter("setCheckedIcon")
fun TextView.setDrawableEnd(isSelected: Boolean) {
    val drawable = if (isSelected) ContextCompat.getDrawable(context, R.drawable.ic_check) else null
    drawable?.let { setCompoundDrawables(null, null, it, null) }
}