package com.janavarro.war_of_suits.components.pokerCard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.databinding.ViewCardContentBinding
import com.janavarro.war_of_suits.utils.EmptySuit
import com.janavarro.war_of_suits.utils.PokerCardUtils
import com.janavarro.war_of_suits.utils.PokerValue
import com.janavarro.war_of_suits.utils.Suit

class PokerCardContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : GridLayout(context, attrs) {

    private val binding = ViewCardContentBinding.inflate(LayoutInflater.from(context), this, true)

    var suit: Suit = EmptySuit
    var value: PokerValue? = null
        set(newValue) {
            field = newValue
            newValue?.let { fillContent(it) }
        }

    private fun fillContent(value: PokerValue) {
        when (value) {
            PokerValue.Two -> {
                // Print 2
                binding.iv01.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv01.setImageResource(suit.image)
                binding.iv21.setImageResource(suit.image)
            }
            PokerValue.Three -> {
                // Print 3
                binding.iv01.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv01.setImageResource(suit.image)
                binding.iv11.setImageResource(suit.image)
                binding.iv21.setImageResource(suit.image)
            }
            PokerValue.Four -> {
                // Print 4
                binding.iv00.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv02.setImageResource(suit.image)
                binding.iv20.setImageResource(suit.image)
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.Five -> {
                // Print 5
                binding.iv00.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv02.setImageResource(suit.image)
                binding.iv20.setImageResource(suit.image)
                binding.iv22.setImageResource(suit.image)
                binding.iv11.setImageResource(suit.image)
            }
            PokerValue.Six -> {
                // Print 6
                binding.iv00.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv10.setImageResource(suit.image)
                binding.iv20.setImageResource(suit.image)
                binding.iv02.setImageResource(suit.image)
                binding.iv12.setImageResource(suit.image)
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.Seven -> {
                // Print 7
                binding.iv00.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv10.setImageResource(suit.image)
                binding.iv20.setImageResource(suit.image)
                binding.iv02.setImageResource(suit.image)
                binding.iv12.setImageResource(suit.image)
                binding.iv22.setImageResource(suit.image)
                binding.iv11.setImageResource(suit.image)
            }
            PokerValue.Eight -> {
                // Print 8
                binding.iv00.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv01.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv10.setImageResource(suit.image)
                binding.iv20.setImageResource(suit.image)
                binding.iv02.setImageResource(suit.image)
                binding.iv12.setImageResource(suit.image)
                binding.iv22.setImageResource(suit.image)
                binding.iv01.setImageResource(suit.image)
                binding.iv21.setImageResource(suit.image)
            }
            PokerValue.Nine -> {
                // Print 9
                binding.iv00.visibility = VISIBLE
                binding.iv01.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv01.setImageResource(suit.image)
                binding.iv02.setImageResource(suit.image)
                binding.iv10.setImageResource(suit.image)
                binding.iv11.setImageResource(suit.image)
                binding.iv12.setImageResource(suit.image)
                binding.iv20.setImageResource(suit.image)
                binding.iv21.setImageResource(suit.image)
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.Ten -> {
                // Print 10
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        PokerValue.Ten.symbol, 80f, context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.J -> {
                // Print J
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        PokerValue.J.symbol, 100f, context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.Q -> {
                // Print Q
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        PokerValue.Q.symbol, 100f, context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.K -> {
                // Print K
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit.image)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        PokerValue.K.symbol, 100f, context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit.image)
            }
            PokerValue.A -> {
                // Print A
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageResource(suit.image)
            }
        }
    }

}