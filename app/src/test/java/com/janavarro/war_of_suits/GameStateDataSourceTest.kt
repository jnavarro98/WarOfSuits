package com.janavarro.war_of_suits

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.model.GameState
import com.janavarro.war_of_suits.utils.GameConstants
import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner
import com.janavarro.war_of_suits.utils.generateDecks
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GameStateDataSourceTest {

    //I use this rule because of the method postValue which runs a background thread
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var gameStateDataSource: GameStateDataSource

    @Before
    fun setup() {
        gameStateDataSource = GameStateDataSource(GameState())
    }

    @Test
    fun p1ScoreAdded() {
        gameStateDataSource.addP1Score()
        Assert.assertEquals(gameStateDataSource.scoreP1.value, GameConstants.SCORE_INCREASE)
    }

    @Test
    fun p2ScoreAdded() {
        gameStateDataSource.addP2Score()
        Assert.assertEquals(gameStateDataSource.scoreP2.value, GameConstants.SCORE_INCREASE)
    }

    //Testing in case someone breaks this setter
    @Test
    fun currentGameStateIsSet() {
        gameStateDataSource.setGameCurrentState(GameCurrentState.Playing)
        Assert.assertEquals(gameStateDataSource.gameCurrentState.value, GameCurrentState.Playing)
    }

    //Testing in case someone breaks this setter
    @Test
    fun winnerIsSet() {
        gameStateDataSource.setWinner(Winner.Player1)
        Assert.assertEquals(gameStateDataSource.gameWinner.value, Winner.Player1)
    }

    @Test
    fun gameIsReset() {
        gameStateDataSource = GameStateDataSource(
            GameState(
                12, 22, GameCurrentState.Playing, generateDecks(), Winner.Player2
            )
        )
        gameStateDataSource.resetGameState()
        Assert.assertEquals(gameStateDataSource.gameWinner.value, Winner.Unset)
        Assert.assertEquals(gameStateDataSource.gameCurrentState.value, GameCurrentState.Finished)
        Assert.assertEquals(gameStateDataSource.scoreP1.value, 0)
        Assert.assertEquals(gameStateDataSource.scoreP2.value, 0)
    }

    @Test
    fun draw27thCardIsNull() {
        repeat(26) { Assert.assertNotNull(gameStateDataSource.drawP1Card()) }
        repeat(26) { Assert.assertNotNull(gameStateDataSource.drawP2Card()) }
        Assert.assertNull(gameStateDataSource.drawP1Card())
        Assert.assertNull(gameStateDataSource.drawP2Card())
    }
}