package com.chunchiehliang.material3.ui.list

import androidx.recyclerview.widget.RecyclerView

abstract class HidingScrollListener : RecyclerView.OnScrollListener() {
    private val HIDE_THRESHOLD = 56
    var scrollDist = 0
    var isLayoutVisible = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (isLayoutVisible && scrollDist > HIDE_THRESHOLD) {
            onHide()
            scrollDist = 0
            isLayoutVisible = false
        } else if (!isLayoutVisible && scrollDist < -HIDE_THRESHOLD) {
            onShow()
            scrollDist = 0
            isLayoutVisible = true
        }

        if ((isLayoutVisible && dy > 0) || (!isLayoutVisible && dy < 0)) {
            scrollDist += dy
        }
    }

    abstract fun onHide()
    abstract fun onShow()
}