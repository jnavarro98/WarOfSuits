package com.janavarro.war_of_suits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.model.Decks
import com.janavarro.war_of_suits.model.GameState
import com.janavarro.war_of_suits.utils.GameConstants.SCORE_INCREASE
import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner
import com.janavarro.war_of_suits.utils.generateDecks

/* Handles operations on gameState and holds details about it. */
class GameStateDataSource(gameState: GameState) {

    private val _scoreP1 = MutableLiveData(gameState.p1Score)
    val scoreP1: LiveData<Int>
        get() = _scoreP1

    private val _scoreP2 = MutableLiveData(gameState.p2Score)
    val scoreP2: LiveData<Int>
        get() = _scoreP2

    var totalScore = 0

    private val _gameCurrentState = MutableLiveData(gameState.gameCurrentState)
    val gameCurrentState: LiveData<GameCurrentState>
        get() = _gameCurrentState

    private val _decks = MutableLiveData(gameState.decks)
    val decks: LiveData<Decks>
        get() = _decks

    private val _gameWinner = MutableLiveData(Winner.Unset)
    val gameWinner: LiveData<Winner>
        get() = _gameWinner


    fun addP1Score() {
        totalScore = totalScore.plus((SCORE_INCREASE))
        _scoreP1.postValue(scoreP1.value?.plus(SCORE_INCREASE))
    }

    fun addP2Score() {
        totalScore = totalScore.plus((SCORE_INCREASE))
        _scoreP2.postValue(scoreP2.value?.plus(SCORE_INCREASE))
    }

    fun setGameCurrentState(newGameState: GameCurrentState) {
        _gameCurrentState.value = newGameState
    }

    fun setWinner(winner: Winner) {
        _gameWinner.value = winner
    }

    fun resetGameState() {
        _gameWinner.value = Winner.Unset
        _gameCurrentState.value = GameCurrentState.Finished
        _scoreP1.value = 0
        _scoreP2.value = 0
        _decks.value = generateDecks()
        totalScore = 0
    }

    fun drawP1Card() = decks.value?.p1Deck?.removeLastOrNull()
    fun drawP2Card() = decks.value?.p2Deck?.removeLastOrNull()

    companion object {
        private var INSTANCE: GameStateDataSource? = null

        fun getGameDataSource(gameState: GameState): GameStateDataSource {
            return synchronized(GameStateDataSource::class) {
                val newInstance = INSTANCE ?: GameStateDataSource(gameState)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}