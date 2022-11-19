package com.janavarro.war_of_suits.model

import android.content.Context
import com.google.gson.Gson
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner
import com.janavarro.war_of_suits.utils.generateDecks

data class GameState(
    val p1Score: Int = 0,
    val p2Score: Int = 0,
    val gameCurrentState: GameCurrentState = GameCurrentState.Finished,
    val decks: Decks = generateDecks(),
    val gameWinner: Winner = Winner.Unset,
    val totalScore: Int = 0,
    val cardP1: Card? = null,
    val cardP2: Card? = null
)