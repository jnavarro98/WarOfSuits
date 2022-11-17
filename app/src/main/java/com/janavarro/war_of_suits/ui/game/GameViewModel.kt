package com.janavarro.war_of_suits.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.ui.game.GameConstants.SCORE_INCREASE
import com.janavarro.war_of_suits.ui.game.GameConstants.TOTAL_SCORE_TO_FINISH
import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner
import com.janavarro.war_of_suits.utils.compareTo

class GameViewModel(private val gameStateDataSource: GameStateDataSource) : ViewModel() {

    var gameStateLiveData = gameStateDataSource.getGameState()

    private val _scoreP1 = MutableLiveData(gameStateLiveData.value?.p1Score ?: 0)
    val scoreP1: LiveData<Int>
        get() = _scoreP1

    private val _scoreP2 = MutableLiveData(gameStateLiveData.value?.p2Score ?: 0)
    val scoreP2: LiveData<Int>
        get() = _scoreP2

    private val _nextTurn = MutableLiveData(GameCurrentState.GameStarted)
    val nextTurn: LiveData<GameCurrentState>
        get() = _nextTurn

    private val _gameWinner = MutableLiveData(Winner.Unset)
    val gameWinner: LiveData<Winner>
        get() = _gameWinner

    private var cardP1: Card? = null
    private var cardP2: Card? = null
    private var totalScore: Int = gameStateLiveData.value?.totalScore ?: 0

    fun setP1Card(drawnCard: Card) {
        cardP1 = drawnCard
    }

    fun setP2Card(drawnCard: Card) {
        cardP2 = drawnCard
    }

    fun checkGameState() {
        cardP1?.let { cardP1 ->
            cardP2?.let { cardP2 ->
                totalScore = totalScore.plus(SCORE_INCREASE)
                if (cardP1.compareTo(cardP2) > 0) {
                    _scoreP1.value = (_scoreP1.value)?.plus(SCORE_INCREASE)
                    if (totalScore == TOTAL_SCORE_TO_FINISH) {
                        _nextTurn.value = GameCurrentState.TurnFinished
                        _gameWinner.value = Winner.Player1
                    } else {
                        _nextTurn.value = GameCurrentState.TurnFinished
                    }
                } else {
                    _scoreP2.value = (_scoreP2.value)?.plus(SCORE_INCREASE)
                    if (totalScore == TOTAL_SCORE_TO_FINISH) {
                        _nextTurn.value = GameCurrentState.TurnFinished
                        _gameWinner.value = Winner.Player2
                    } else {
                        _nextTurn.value = GameCurrentState.TurnFinished
                    }
                }
            }
        }
    }

    fun finishTurn() {
        _nextTurn.value = GameCurrentState.Playing
        cardP1 = null
        cardP2 = null
    }

//    fun finishGame() {
//        if (_gameWinner.value != Winner.Unset) {
//            _nextTurn.value = GameCurrentState.GameStarted
//            _scoreP1.value = 0
//            _scoreP2.value = 0
//            gameStateDataSource.restartGame()
//        }
//    }

    fun resetGameState() {
        _nextTurn.value = GameCurrentState.GameStarted
        _scoreP1.value = 0
        _scoreP2.value = 0
        gameStateDataSource.restartGame()
        gameStateLiveData = gameStateDataSource.getGameState()
    }

}

object GameConstants {
    const val SCORE_INCREASE = 2
    const val TOTAL_SCORE_TO_FINISH = 52
}