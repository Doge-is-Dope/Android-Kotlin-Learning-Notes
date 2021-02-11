package com.chunchiehliang.minipaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.core.content.res.ResourcesCompat

class MyCanvasView(context: Context) : View(context) {

    // The variables are for caching what has been drawn before
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)

    // This callback method is called by the Android system with the changed screen dimensions,
    // that is, with a new width and height (to change to) and the old width and height (to change from)
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        // Recycle extraBitmap before creating the next one to avoid memory leak
        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        // The third argument is the bitmap color configuration. ARGB_8888 stores each color in 4 bytes and is recommended
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)
    }

    // Override onDraw() and draw the contents of the cached extraBitmap
    // on the canvas associated with the view
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Provide the bitmap, the x and y coordinates (in pixels) of the top left corner,
        // and null for the Paint
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }
}