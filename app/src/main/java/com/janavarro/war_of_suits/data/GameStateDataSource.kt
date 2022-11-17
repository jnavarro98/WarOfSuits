package com.janavarro.war_of_suits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.model.GameState
import com.janavarro.war_of_suits.utils.generateNewGame

/* Handles operations on gameState and holds details about it. */
class GameStateDataSource(gameState: GameState) {
    private val initialGameState = gameState
    private var gameStateLiveData = MutableLiveData(initialGameState)

    fun getGameState(): LiveData<GameState> {
        return gameStateLiveData
    }

    fun restartGame() {
        gameStateLiveData.postValue(generateNewGame())
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