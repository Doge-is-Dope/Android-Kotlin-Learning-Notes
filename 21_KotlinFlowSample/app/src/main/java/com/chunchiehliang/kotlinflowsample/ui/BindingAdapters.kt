package com.chunchiehliang.kotlinflowsample.ui

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("app:drawableEndCompat")
fun TextView.setDrawableEnd(drawable: Drawable?) {
    drawable?.let {
        setIntrinsicBounds(it)
        setCompoundDrawables(null, null, it, null)
    }
}

private fun setIntrinsicBounds(drawable: Drawable?) {
    drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
}