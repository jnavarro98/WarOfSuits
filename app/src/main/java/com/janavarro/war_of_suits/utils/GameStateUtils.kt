package com.janavarro.war_of_suits.utils

import com.janavarro.war_of_suits.model.GameState

enum class Winner {
    Player1,
    Player2,
    Unset
}

enum class GameCurrentState {
    Playing,
    TurnFinished,
    GameStarted
}

fun generateNewGame() = GameState(0, 0, generateDecks(), 0)

object GameConstants {
    const val SCORE_INCREASE = 2
    const val TOTAL_SCORE_TO_FINISH = 52
}