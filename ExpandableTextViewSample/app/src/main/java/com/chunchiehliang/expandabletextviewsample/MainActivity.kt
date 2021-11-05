package com.chunchiehliang.expandabletextviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

/**
 * Reference: https://juejin.cn/post/6844903908028973070
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewSuffixWrapper =
            TextViewSuffixWrapper(findViewById(R.id.textView)).apply wrapper@{
                this.mainContent =
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                // this.mainContent = "caption>>caption{filterTagId3=filterTagName3, filterTagId4=filterTagName4, filterTagId1=filterTagName1, filterTagId2=filterTagName2, filterTagId0=filterTagName0}"
                this.suffix = "...查看更多"
                this.suffix?.apply {
                    suffixColor(
                        "...".length,
                        this.length,
                        R.color.purple_200,
                        listener = View.OnClickListener { view ->
                            if (this@wrapper.isCollapsed) {
                                this@wrapper.expand()
                            }
                        })
                }
                this.transition?.duration = 200
                sceneRoot = this.textView.parent.parent.parent as ViewGroup
                collapse(false)
                this.textView.setOnClickListener {
//                    toast("click view")
                }
            }

        findViewById<View>(R.id.btn_toggle).setOnClickListener {
            textViewSuffixWrapper.toggle()
        }
    }
}