package com.janavarro.war_of_suits.utils

enum class Winner {
    Player1,
    Player2,
    Tie,
    Unset
}

enum class GameCurrentState {
    Playing,
    TurnFinished,
    Finished
}

object GameConstants {
    const val SCORE_INCREASE = 2
    const val MAX_SCORE = 52
}