package com.janavarro.warofsuits.components.pokerCard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.databinding.ViewCardContentBinding
import com.janavarro.warofsuits.utils.DrawUtils
import com.janavarro.warofsuits.utils.EmptySuit
import com.janavarro.warofsuits.utils.PokerCardConstants.ROYAL_CARDS_SYMBOL_SIZE
import com.janavarro.warofsuits.utils.PokerValue
import com.janavarro.warofsuits.utils.Suit

/*
* This component is designed to automatically draw the content of a poker card
* once you pass the Card object values
* */
class PokerCardContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : GridLayout(context, attrs) {

    //Using view binding instead of data binding because is easier to customize this way
    private val binding = ViewCardContentBinding.inflate(LayoutInflater.from(context), this, true)

    var suit: Suit = EmptySuit
    var value: PokerValue? = null
        set(newValue) {
            field = newValue
            newValue?.let { fillContent(it) }
        }

    //Each card has its design
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
                    DrawUtils().textAsBitmap(
                        value.symbol,
                        ROYAL_CARDS_SYMBOL_SIZE,
                        context.getColor(R.color.black)
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
                    DrawUtils().textAsBitmap(
                        value.symbol,
                        ROYAL_CARDS_SYMBOL_SIZE,
                        context.getColor(R.color.black)
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
                    DrawUtils().textAsBitmap(
                        value.symbol,
                        ROYAL_CARDS_SYMBOL_SIZE,
                        context.getColor(R.color.black)
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
                    DrawUtils().textAsBitmap(
                        value.symbol,
                        ROYAL_CARDS_SYMBOL_SIZE,
                        context.getColor(R.color.black)
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