package com.chunchiehliang.expandabletextviewsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.socialview.SocialTextView

/**
 * Reference: https://juejin.cn/post/6844903908028973070
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<SocialTextView>(R.id.textView)
        textView.setOnHashtagClickListener { view, text ->
            Log.d("MainActivity", "Clicked hashtag: $text")
        }
        textView.setOnMentionClickListener { view, text ->
            Log.d("MainActivity", "Clicked mention: $text")
        }
        val wrapper = textView.makeExpandableText("查看更多", R.color.purple_200)


        findViewById<View>(R.id.btn_toggle).setOnClickListener {
            wrapper.toggle()
        }
    }
}
