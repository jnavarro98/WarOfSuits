package com.janavarro.war_of_suits.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.janavarro.war_of_suits.data.DecksDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.ui.game.GameConstants.SCORE_INCREASE
import com.janavarro.war_of_suits.ui.game.GameConstants.TOTAL_SCORE_TO_FINISH
import com.janavarro.war_of_suits.utils.Player
import com.janavarro.war_of_suits.utils.compareTo

class GameViewModel(val decksDataSource: DecksDataSource) : ViewModel() {

    val decksLiveData = decksDataSource.getDeck()

    private val _scoreP1 = MutableLiveData(0)
    val scoreP1: LiveData<Int>
        get() = _scoreP1

    private val _scoreP2 = MutableLiveData(0)
    val scoreP2: LiveData<Int>
        get() = _scoreP2

    private val _nextTurn = MutableLiveData(false)
    val nextTurn: LiveData<Boolean>
        get() = _nextTurn

    private val _gameWinner = MutableLiveData(Player.Unset)
    val gameWinner: LiveData<Player>
        get() = _gameWinner

    private var cardP1: Card? = null
    private var cardP2: Card? = null
    private var totalScore = 0

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
                        _nextTurn.value = true
                        _gameWinner.value = Player.Player1
                    } else {
                        _nextTurn.value = true
                    }
                } else {
                    _scoreP2.value = (_scoreP2.value)?.plus(SCORE_INCREASE)
                    if (totalScore == TOTAL_SCORE_TO_FINISH) {
                        _nextTurn.value = true
                        _gameWinner.value = Player.Player2
                    } else {
                        _nextTurn.value = true
                    }
                }
            }
        }
    }

    fun finishTurn() {
        _nextTurn.value = false
        cardP1 = null
        cardP2 = null
    }

    fun finishGame() {
        if (_gameWinner.value != Player.Unset) {
            _nextTurn.value = false
            _scoreP1.value = 0
            _scoreP2.value = 0
            decksDataSource.regenerateDeck()
        }
    }

    fun resetGameState() {
        _nextTurn.value = false
        _scoreP1.value = 0
        _scoreP2.value = 0
        decksDataSource.regenerateDeck()
    }

}

object GameConstants {
    const val SCORE_INCREASE = 2
    const val TOTAL_SCORE_TO_FINISH = 52
}