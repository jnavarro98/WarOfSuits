package com.janavarro.warofsuits.model

import com.janavarro.warofsuits.utils.PokerValue
import com.janavarro.warofsuits.utils.Suit

data class Card(
    val pokerValue: PokerValue,
    val suit: Suit
)
