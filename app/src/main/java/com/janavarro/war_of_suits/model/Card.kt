package com.janavarro.war_of_suits.model

import com.janavarro.war_of_suits.utils.PokerValue
import com.janavarro.war_of_suits.utils.Suit

data class Card(
    val pokerValue: PokerValue,
    val suit: Suit
)
