package com.chunchiehliang.expandabletextviewsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chunchiehliang.expandabletextviewsample.databinding.ActivityMainBinding
import com.chunchiehliang.socialview.SocialTextView

/**
 * Reference: https://juejin.cn/post/6844903908028973070
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvExpandableTextView.setOnHashtagClickListener { view, text ->
                Log.d("MainActivity", "Clicked hashtag: $text")
            }

            tvExpandableTextView.setOnMentionClickListener { view, text ->
                Log.d("MainActivity", "Clicked mention: $text")
            }

            val wrapper = tvExpandableTextView.makeExpandableText("查看更多", R.color.purple_200)
            btnToggle.setOnClickListener { wrapper.toggle() }
        }

    }
}
