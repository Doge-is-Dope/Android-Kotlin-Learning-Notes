package com.chunchiehliang.expandabletextviewsample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
