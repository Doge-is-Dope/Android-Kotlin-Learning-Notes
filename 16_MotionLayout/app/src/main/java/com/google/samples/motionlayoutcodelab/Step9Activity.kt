package com.google.samples.motionlayoutcodelab

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.animation.addListener
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Step9Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step9)
        val btnFade = findViewById<Button>(R.id.btn_fade)
        val mask = findViewById<View>(R.id.view_mask)
        val transitionListener = object : MotionLayout.TransitionListener {

            override fun onTransitionStarted(p0: MotionLayout?, startId: Int, endId: Int) {}

            override fun onTransitionChange(
                layout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                if (currentId == R.id.set4)
                    Log.d("Step9", "finished set4 $currentId")
                else
                    Log.d("Step9", "finished: $currentId")
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float
            ) {
            }
        }

        findViewById<MotionLayout>(R.id.motion_layout).setTransitionListener(transitionListener)

        btnFade.setOnClickListener {
//            val animator = ValueAnimator.ofFloat(1.0f, 0.0f)
//            animator.duration = 1000
//            animator.addUpdateListener { valueAnimator ->
//                val value = valueAnimator?.animatedValue as Float
//                tvFade.alpha = value
//            }
            val animatorShow = ObjectAnimator.ofFloat(mask, "alpha", 0.0f, 0.2f)
            val animatorHide = ObjectAnimator.ofFloat(mask, "alpha", 0.2f, 0.0f)
            val animatorSet = AnimatorSet()
            animatorSet.duration = 1000
            animatorSet.playSequentially(animatorShow, animatorHide)
            animatorSet.start()
        }
    }
}
