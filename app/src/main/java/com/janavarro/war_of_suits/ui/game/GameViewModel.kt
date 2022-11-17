package com.janavarro.war_of_suits.ui.game

import androidx.lifecycle.ViewModel
import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.utils.GameConstants.TOTAL_SCORE_TO_FINISH
import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner
import com.janavarro.war_of_suits.utils.compareTo

class GameViewModel(private val gameStateDataSource: GameStateDataSource) : ViewModel() {

    val scoreP1LiveData = gameStateDataSource.scoreP1
    val scoreP2LiveData = gameStateDataSource.scoreP2
    val gameWinnerLiveData = gameStateDataSource.gameWinner
    val gameCurrentStateLiveData = gameStateDataSource.gameCurrentState
    var cardP1: Card? = null
    var cardP2: Card? = null


    fun checkGameState() {
        cardP1?.let { cardP1 ->
            cardP2?.let { cardP2 ->
                if (cardP1.compareTo(cardP2) > 0) {
                    gameStateDataSource.addP1Score()
                    if (gameStateDataSource.totalScore == TOTAL_SCORE_TO_FINISH)
                        gameStateDataSource.setWinner(Winner.Player1)
                } else {
                    gameStateDataSource.addP2Score()
                    if (gameStateDataSource.totalScore == TOTAL_SCORE_TO_FINISH)
                        gameStateDataSource.setWinner(Winner.Player2)
                }
                gameStateDataSource.setGameCurrentState(GameCurrentState.TurnFinished)
            }
        }
    }

    fun drawP1Card() = gameStateDataSource.drawP1Card()
    fun drawP2Card() = gameStateDataSource.drawP2Card()

    fun finishTurn() {
        gameStateDataSource.setGameCurrentState(GameCurrentState.Playing)
        cardP1 = null
        cardP2 = null
    }

    fun resetGameState() {
        gameStateDataSource.resetGameState()
    }

}