package com.janavarro.war_of_suits.model

import com.janavarro.war_of_suits.utils.generateDecks

data class GameState(
    var p1Score: Int = 0,
    var p2Score: Int = 0,
    var currentDeck: Decks = generateDecks(),
    var totalScore: Int = 0
)