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
                    val p1CurrentPoints = gameStateDataSource.addP1Score()
                    if (gameStateDataSource.totalScore == TOTAL_SCORE_TO_FINISH) {
                        if (p1CurrentPoints != TOTAL_SCORE_TO_FINISH / 2) {
                            gameStateDataSource.setWinner(Winner.Player1)
                        } else {
                            gameStateDataSource.setWinner(Winner.Tie)
                        }
                    }
                } else {
                    val p2CurrentPoints = gameStateDataSource.addP2Score()
                    if (gameStateDataSource.totalScore == TOTAL_SCORE_TO_FINISH) {
                        if (p2CurrentPoints != TOTAL_SCORE_TO_FINISH / 2) {
                            gameStateDataSource.setWinner(Winner.Player2)
                        } else {
                            gameStateDataSource.setWinner(Winner.Tie)
                        }
                    }
                }
                gameStateDataSource.setGameCurrentState(GameCurrentState.TurnFinished)
            }
        }
    }

    fun drawP1Card() = gameStateDataSource.drawP1Card()
    fun drawP2Card() = gameStateDataSource.drawP2Card()

    fun startNewTurn() {
        gameStateDataSource.setGameCurrentState(GameCurrentState.Playing)
        cardP1 = null
        cardP2 = null
    }

    fun resetGameState() {
        gameStateDataSource.resetGameState()
    }

}