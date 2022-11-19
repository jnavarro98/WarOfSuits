package com.janavarro.war_of_suits.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

class DrawUtils {

    fun textAsBitmap(text: String, textSize: Float, textColor: Int): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline: Float = -paint.ascent()
        val width = (paint.measureText(text) + 0.5f)
        val height = (baseline + paint.descent() + 0.5f)
        val image = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text, 0F, baseline, paint)
        return image
    }
}