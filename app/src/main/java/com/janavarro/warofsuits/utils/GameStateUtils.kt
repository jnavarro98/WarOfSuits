package com.janavarro.warofsuits.utils

enum class Winner {
    Player1,
    Player2,
    Tie,
    Unset
}

enum class GameCurrentState {
    Playing,
    TurnFinished,
    DrawingCard,
    Finished
}

object GameConstants {
    const val SCORE_INCREASE = 2
    const val AMOUNT_OF_POKER_CARDS = 52
    const val CARDS_PER_PLAYER_DECK = AMOUNT_OF_POKER_CARDS / 2
}