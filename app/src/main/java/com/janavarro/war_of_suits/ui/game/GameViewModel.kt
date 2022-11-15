package com.janavarro.war_of_suits.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.janavarro.war_of_suits.data.DecksDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.ui.game.GameConstants.SCORE_INCREASE
import com.janavarro.war_of_suits.utils.compareTo

class GameViewModel(val decksDataSource: DecksDataSource) : ViewModel() {

    val gameStateLiveData = decksDataSource.getDeckList()

    private val _scoreP1 = MutableLiveData(0)
    val scoreP1: LiveData<Int>
        get() = _scoreP1

    private val _scoreP2 = MutableLiveData(0)
    val scoreP2: LiveData<Int>
        get() = _scoreP2

    private var cardP1: Card? = null
    private var cardP2: Card? = null

    private fun addScoreP2() {
        _scoreP2.value = (_scoreP2.value)?.plus(SCORE_INCREASE)
    }

    fun setP1Card(drawnCard: Card) {
        cardP1 = drawnCard
        checkGameState()
    }

    fun setP2Card(drawnCard: Card) {
        cardP2 = drawnCard
        checkGameState()
    }

    private fun checkGameState() {
        cardP1?.let { cardP1 ->
            cardP2?.let { cardP2 ->
                if (cardP1.compareTo(cardP2) > 0) {
                    _scoreP1.value = (_scoreP1.value)?.plus(SCORE_INCREASE)
                } else {
                    _scoreP2.value = (_scoreP2.value)?.plus(SCORE_INCREASE)
                }
            }
        }
    }

    private fun checkResult() {

    }
}

object GameConstants {
    const val SCORE_INCREASE = 2
}