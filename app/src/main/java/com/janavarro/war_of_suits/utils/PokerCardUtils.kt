package com.janavarro.war_of_suits.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.janavarro.war_of_suits.R


class PokerCardUtils {
    sealed class Suit(@DrawableRes val image: Int)
    class Diamonds() : Suit(R.drawable.ic_diamond)
    class Clubs() : Suit(R.drawable.ic_club)
    class Hearts() : Suit(R.drawable.ic_heart)
    class Spade() : Suit(R.drawable.ic_spade)

    sealed class Value(val score: Int, val symbol: String)
    class Two() : Value(0, "2")
    class Three() : Value(1, "3")
    class Four() : Value(2, "4")
    class Five() : Value(3, "5")
    class Six() : Value(4, "6")
    class Seven() : Value(5, "7")
    class Eight() : Value(6, "8")
    class Nine() : Value(7, "9")
    class Ten() : Value(8, "10")
    class J() : Value(9, "J")
    class Q() : Value(10, "Q")
    class K() : Value(11, "K")
    class A() : Value(12, "A")
    class Empty() : Value(-1, "")

    fun textAsBitmap(text: String, textSize: Float, textColor: Int): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline: Float = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.5f) // round
        val height = (baseline + paint.descent() + 0.5f)
        val image = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text, 0F, baseline, paint)
        return image
    }
}