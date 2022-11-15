package com.janavarro.war_of_suits.components.pokerCard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.databinding.ViewCardContentBinding
import com.janavarro.war_of_suits.databinding.ViewIconButtonBinding
import com.janavarro.war_of_suits.utils.PokerCardUtils

class PokerCardContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : GridLayout(context, attrs) {

    private val binding = ViewCardContentBinding.inflate(LayoutInflater.from(context), this, true)

    @DrawableRes
    var suit: Int = -1
    var value: Int = -1
        set(newValue) {
            field = newValue
            fillContent(newValue)
        }

    private fun fillContent(value: Int) {
        when (value) {
            0 -> {
                // Print 2
                binding.iv01.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv01.setImageResource(suit)
                binding.iv21.setImageResource(suit)
            }
            1 -> {
                // Print 3
                binding.iv01.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv01.setImageResource(suit)
                binding.iv11.setImageResource(suit)
                binding.iv21.setImageResource(suit)
            }
            2 -> {
                // Print 4
                binding.iv00.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv02.setImageResource(suit)
                binding.iv20.setImageResource(suit)
                binding.iv22.setImageResource(suit)
            }
            3 -> {
                // Print 5
                binding.iv00.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv02.setImageResource(suit)
                binding.iv20.setImageResource(suit)
                binding.iv22.setImageResource(suit)
                binding.iv11.setImageResource(suit)
            }
            4 -> {
                // Print 6
                binding.iv00.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv10.setImageResource(suit)
                binding.iv20.setImageResource(suit)
                binding.iv02.setImageResource(suit)
                binding.iv12.setImageResource(suit)
                binding.iv22.setImageResource(suit)
            }
            5 -> {
                // Print 7
                binding.iv00.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv11.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv10.setImageResource(suit)
                binding.iv20.setImageResource(suit)
                binding.iv02.setImageResource(suit)
                binding.iv12.setImageResource(suit)
                binding.iv22.setImageResource(suit)
                binding.iv11.setImageResource(suit)
            }
            6 -> {
                // Print 8
                binding.iv00.visibility = VISIBLE
                binding.iv10.visibility = VISIBLE
                binding.iv20.visibility = VISIBLE
                binding.iv02.visibility = VISIBLE
                binding.iv12.visibility = VISIBLE
                binding.iv22.visibility = VISIBLE
                binding.iv01.visibility = VISIBLE
                binding.iv21.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv10.setImageResource(suit)
                binding.iv20.setImageResource(suit)
                binding.iv02.setImageResource(suit)
                binding.iv12.setImageResource(suit)
                binding.iv22.setImageResource(suit)
                binding.iv01.setImageResource(suit)
                binding.iv21.setImageResource(suit)
            }
            7 -> {
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
                binding.iv00.setImageResource(suit)
                binding.iv01.setImageResource(suit)
                binding.iv02.setImageResource(suit)
                binding.iv10.setImageResource(suit)
                binding.iv11.setImageResource(suit)
                binding.iv12.setImageResource(suit)
                binding.iv20.setImageResource(suit)
                binding.iv21.setImageResource(suit)
                binding.iv22.setImageResource(suit)
            }
            8 -> {
                // Print 10
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        "10", 100f,
                        if (suit == PokerCardUtils.Hearts().image || suit == PokerCardUtils.Diamonds().image) context.getColor(
                            R.color.red
                        )
                        else context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit)
            }
            9 -> {
                // Print J
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        "J", 100f,
                        if (suit == PokerCardUtils.Hearts().image || suit == PokerCardUtils.Diamonds().image) context.getColor(
                            R.color.red
                        )
                        else context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit)
            }
            10 -> {
                // Print Q
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        "Q", 100f,
                        if (suit == PokerCardUtils.Hearts().image || suit == PokerCardUtils.Diamonds().image) context.getColor(
                            R.color.red
                        )
                        else context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit)
            }
            11 -> {
                // Print K
                binding.iv00.visibility = VISIBLE
                binding.iv00.setImageResource(suit)
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageBitmap(
                    PokerCardUtils().textAsBitmap(
                        "K", 100f,
                        if (suit == PokerCardUtils.Hearts().image || suit == PokerCardUtils.Diamonds().image) context.getColor(
                            R.color.red
                        )
                        else context.getColor(R.color.black)
                    )
                )
                binding.iv22.visibility = VISIBLE
                binding.iv22.setImageResource(suit)
            }
            12 -> {
                // Print A
                binding.iv11.visibility = VISIBLE
                binding.iv11.setImageResource(suit)
            }
        }
    }

}