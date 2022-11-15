package com.janavarro.war_of_suits.model

import androidx.annotation.DrawableRes

data class Card(
    val value: Int,
    @DrawableRes val suit: Int
)
