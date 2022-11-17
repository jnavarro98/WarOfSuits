package com.janavarro.war_of_suits.model

import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner
import com.janavarro.war_of_suits.utils.generateDecks

data class GameState(
    val p1Score: Int = 0,
    val p2Score: Int = 0,
    val gameCurrentState: GameCurrentState = GameCurrentState.Finished,
    val decks: Decks = generateDecks(),
    val gameWinner: Winner = Winner.Unset
)