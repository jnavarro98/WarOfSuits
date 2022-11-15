package com.janavarro.war_of_suits.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.model.Card

sealed class Suit(@DrawableRes val image: Int, open val score: Int = -1)
class Diamonds(override val score: Int = -1) : Suit(R.drawable.ic_diamond, score)
class Clubs(override val score: Int = -1) : Suit(R.drawable.ic_club, score)
class Hearts(override val score: Int = -1) : Suit(R.drawable.ic_heart, score)
class Spades(override val score: Int = -1) : Suit(R.drawable.ic_spade, score)
object EmptySuit : Suit(-1, -1)

enum class PokerValue(val score: Int, val symbol: String) {
    Two(0, "2"),
    Three(1, "3"),
    Four(2, "4"),
    Five(3, "5"),
    Six(4, "6"),
    Seven(5, "7"),
    Eight(6, "8"),
    Nine(7, "9"),
    Ten(8, "10"),
    J(9, "J"),
    Q(10, "Q"),
    K(11, "K"),
    A(12, "A")
}

class PokerCardUtils {


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

fun Card.compareTo(card2: Card): Int {
    val result = this.pokerValue.score.compareTo(card2.pokerValue.score)
    return if (result == 0) {
        this.suit.score.compareTo(card2.suit.score)
    } else {
        result
    }
}