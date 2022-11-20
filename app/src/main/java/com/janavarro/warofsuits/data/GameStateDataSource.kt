package com.janavarro.warofsuits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.warofsuits.model.Card
import com.janavarro.warofsuits.model.Decks
import com.janavarro.warofsuits.model.GameState
import com.janavarro.warofsuits.utils.GameConstants.SCORE_INCREASE
import com.janavarro.warofsuits.utils.GameCurrentState
import com.janavarro.warofsuits.utils.Winner
import com.janavarro.warofsuits.utils.generateDecks

/* Handles operations on gameState and holds details about it.*/
class GameStateDataSource(gameState: GameState) {

    private val _scoreP1 = MutableLiveData(gameState.p1Score)
    val scoreP1: LiveData<Int>
        get() = _scoreP1

    private val _scoreP2 = MutableLiveData(gameState.p2Score)
    val scoreP2: LiveData<Int>
        get() = _scoreP2

    private val _gameCurrentState = MutableLiveData(gameState.gameCurrentState)
    val gameCurrentState: LiveData<GameCurrentState>
        get() = _gameCurrentState

    private var decks: Decks = gameState.decks

    private val _gameWinner = MutableLiveData(Winner.Unset)
    val gameWinner: LiveData<Winner>
        get() = _gameWinner

    var totalScore = gameState.totalScore
        private set

    var cardP1: Card? = gameState.cardP1
    var cardP2: Card? = gameState.cardP2

    fun addP1Score() {
        totalScore = totalScore.plus(SCORE_INCREASE)
        val p1Score = scoreP1.value?.plus(SCORE_INCREASE)
        //I post the value instead of setting it because I want a little delay for race condition reasons
        //so the winner is set before triggering the turn winner dialog
        _scoreP1.postValue(p1Score)
    }

    fun addP2Score() {
        totalScore = totalScore.plus(SCORE_INCREASE)
        val p2Score = scoreP2.value?.plus(SCORE_INCREASE)
        //I post the value instead of setting it because I want a little delay for race condition reasons
        //so the winner is set before triggering the turn winner dialog
        _scoreP2.postValue(p2Score)
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
        decks = generateDecks()
        totalScore = 0
        cardP1 = null
        cardP2 = null
    }

    fun drawP1Card(): Card? {
        val drawnCard = decks.p1Deck.removeLastOrNull()
        cardP1 = drawnCard
        return cardP1
    }

    fun drawP2Card(): Card? {
        val drawnCard = decks.p2Deck.removeLastOrNull()
        cardP2 = drawnCard
        return cardP2
    }

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