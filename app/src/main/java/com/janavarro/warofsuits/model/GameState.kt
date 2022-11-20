package com.janavarro.warofsuits.model

import com.janavarro.warofsuits.utils.GameCurrentState
import com.janavarro.warofsuits.utils.Winner
import com.janavarro.warofsuits.utils.generateDecks

data class GameState(
    val p1Score: Int = 0,
    val p2Score: Int = 0,
    val gameCurrentState: GameCurrentState = GameCurrentState.Finished,
    val decks: Decks = generateDecks(),
    val gameWinner: Winner = Winner.Unset,
    val drawnCards: Int = 0,
    val cardP1: Card? = null,
    val cardP2: Card? = null
)