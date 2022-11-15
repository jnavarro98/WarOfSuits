package com.janavarro.war_of_suits.components.pokerCard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.databinding.ViewCardBinding
import com.janavarro.war_of_suits.utils.PokerCardUtils

class PokerCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = ViewCardBinding.inflate(LayoutInflater.from(context), this, true)

    var suit: Int = 0
        set(imageId) {
            field = imageId
            binding.cardContent.suit = suit
        }

    var value: PokerCardUtils.Value = PokerCardUtils.Empty()
        set(newTitle) {
            field = newTitle
            binding.topValue.text = field.symbol
            binding.bottomValue.text = field.symbol
            binding.cardContent.value = field.score
        }

}