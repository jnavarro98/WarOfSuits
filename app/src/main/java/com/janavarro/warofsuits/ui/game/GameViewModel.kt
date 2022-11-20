package com.janavarro.warofsuits.ui.game

import androidx.lifecycle.ViewModel
import com.janavarro.warofsuits.data.GameStateDataSource
import com.janavarro.warofsuits.model.Card
import com.janavarro.warofsuits.utils.GameConstants.MAX_SCORE
import com.janavarro.warofsuits.utils.GameCurrentState
import com.janavarro.warofsuits.utils.Winner
import com.janavarro.warofsuits.utils.compareTo

class GameViewModel(private val gameStateDataSource: GameStateDataSource) : ViewModel() {

    val scoreP1LiveData = gameStateDataSource.scoreP1
    val scoreP2LiveData = gameStateDataSource.scoreP2
    val gameWinnerLiveData = gameStateDataSource.gameWinner
    val gameCurrentStateLiveData = gameStateDataSource.gameCurrentState
    private var cardP1: Card? = null
    private var cardP2: Card? = null


    private fun checkGameState() {
        cardP1?.let { cardP1 ->
            cardP2?.let { cardP2 ->
                if (cardP1.compareTo(cardP2) > 0) {
                    gameStateDataSource.addP1Score()
                } else {
                    gameStateDataSource.addP2Score()
                }
                if (gameStateDataSource.totalScore == MAX_SCORE) {
                    checkWinner()
                }
                gameStateDataSource.setGameCurrentState(GameCurrentState.TurnFinished)
            }
        }
    }

    private fun checkWinner() {
        when (gameStateDataSource.scoreP1.value?.compareTo(gameStateDataSource.scoreP2.value!!)) {
            1 -> gameStateDataSource.setWinner(Winner.Player1)
            -1 -> gameStateDataSource.setWinner(Winner.Player2)
            0 -> gameStateDataSource.setWinner(Winner.Tie)
        }
    }

    fun drawP1Card(): Card? {
        val drawnCard = gameStateDataSource.drawP1Card()
        cardP1 = drawnCard
        checkGameState()
        return drawnCard
    }

    fun drawP2Card(): Card? {
        val drawnCard = gameStateDataSource.drawP2Card()
        cardP2 = drawnCard
        checkGameState()
        return drawnCard
    }

    fun startNewTurn() {
        gameStateDataSource.setGameCurrentState(GameCurrentState.Playing)
        cardP1 = null
        cardP2 = null
    }

    fun resetGameState() {
        gameStateDataSource.resetGameState()
    }

}