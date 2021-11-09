package com.chunchiehliang.expandabletextviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes

/**
 * Reference: https://juejin.cn/post/6844903908028973070
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wrapper =
            findViewById<TextView>(R.id.textView).makeExpandableText("查看更多", R.color.purple_200)

        findViewById<View>(R.id.btn_toggle).setOnClickListener {
            wrapper.toggle()
        }
    }
}