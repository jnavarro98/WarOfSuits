package com.janavarro.war_of_suits.components.pokerCard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.janavarro.war_of_suits.databinding.ViewCardBinding
import com.janavarro.war_of_suits.utils.EmptySuit
import com.janavarro.war_of_suits.utils.PokerValue
import com.janavarro.war_of_suits.utils.Suit

/*
* This component is designed to automatically draw the border of a poker card
* once you pass the Card object values
* */
class PokerCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = ViewCardBinding.inflate(LayoutInflater.from(context), this, true)

    var suit: Suit = EmptySuit
        set(imageId) {
            field = imageId
            binding.cardContent.suit = suit
            drawStars()
        }

    var pokerValue: PokerValue? = null
        set(newPokerValue) {
            field = newPokerValue
            newPokerValue?.let {
                binding.topValue.text = newPokerValue.symbol
                binding.bottomValue.text = newPokerValue.symbol
                binding.cardContent.value = newPokerValue
            }
        }


    private fun drawStars() {
        when (suit.score) {
            0 -> {
                binding.topStar.visibility = VISIBLE
            }
            1 -> {
                binding.leftStar.visibility = VISIBLE
                binding.rightStar.visibility = VISIBLE
            }
            2 -> {
                binding.topStar.visibility = VISIBLE
                binding.leftStar.visibility = VISIBLE
                binding.rightStar.visibility = VISIBLE
            }
            3 -> {
                binding.topStar.visibility = VISIBLE
                binding.bottomStar.visibility = VISIBLE
                binding.leftStar.visibility = VISIBLE
                binding.rightStar.visibility = VISIBLE
            }
        }
    }
}