package com.janavarro.warofsuits.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.janavarro.warofsuits.data.GameStateDataSource
import com.janavarro.warofsuits.model.Card
import com.janavarro.warofsuits.model.GameState
import com.janavarro.warofsuits.utils.*
import com.janavarro.warofsuits.utils.GameConstants.CARDS_PER_PLAYER_DECK
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

    //Testing in case this setter breaks
    @Test
    fun currentGameStateIsSet() {
        gameStateDataSource.setGameCurrentState(GameCurrentState.Playing)
        Assert.assertEquals(gameStateDataSource.gameCurrentState.value, GameCurrentState.Playing)
    }

    //Testing in case this setter breaks
    @Test
    fun winnerIsSet() {
        gameStateDataSource.setWinner(Winner.Player1)
        Assert.assertEquals(gameStateDataSource.gameWinner.value, Winner.Player1)
    }

    @Test
    fun `game state is reset`() {
        //Empty deck so we know it gets regenerated
        val testUsedDeck = generateDecks()
        repeat(CARDS_PER_PLAYER_DECK) { testUsedDeck.p1Deck.removeFirst() }
        repeat(CARDS_PER_PLAYER_DECK) { testUsedDeck.p2Deck.removeFirst() }
        //Game State of a game that has just finished but hasn't been reset
        gameStateDataSource = GameStateDataSource(
            GameState(
                p1Score = 26,
                p2Score = 26,
                gameCurrentState = GameCurrentState.TurnFinished,
                decks = testUsedDeck,
                gameWinner = Winner.Tie,
                drawnCards = 52,
                cardP1 = Card(PokerValue.A, Hearts(6))
            )
        )

        gameStateDataSource.resetGameState()

        Assert.assertEquals(gameStateDataSource.gameWinner.value, Winner.Unset)
        Assert.assertEquals(gameStateDataSource.gameCurrentState.value, GameCurrentState.Finished)
        Assert.assertEquals(gameStateDataSource.scoreP1.value, 0)
        Assert.assertEquals(gameStateDataSource.scoreP2.value, 0)
        Assert.assertNull(gameStateDataSource.cardP1)
        Assert.assertNull(gameStateDataSource.cardP2)
        Assert.assertNotNull(gameStateDataSource.drawP1Card())
        Assert.assertNotNull(gameStateDataSource.drawP2Card())
        Assert.assertEquals(gameStateDataSource.drawnCards, 0)
    }

    @Test
    fun `player deck has 26 cards`() {
        repeat(CARDS_PER_PLAYER_DECK) { Assert.assertNotNull(gameStateDataSource.drawP1Card()) }
        repeat(CARDS_PER_PLAYER_DECK) { Assert.assertNotNull(gameStateDataSource.drawP2Card()) }
        Assert.assertNull(gameStateDataSource.drawP1Card())
        Assert.assertNull(gameStateDataSource.drawP2Card())
    }

    @Test
    fun `cards are drawn correctly`() {
        val testDecks = generateDecks()
        //Copy of our random generated deck
        val p1Deck = testDecks.p1Deck.toList()
        val p2Deck = testDecks.p2Deck.toList()
        val testGameState = GameState(decks = testDecks)
        gameStateDataSource = GameStateDataSource(testGameState)
        //Checking that we are using the deck we passed
        for (i in 0 until CARDS_PER_PLAYER_DECK) {
            Assert.assertEquals(
                p1Deck[i],
                gameStateDataSource.drawP1Card()
            )
            Assert.assertEquals(
                p2Deck[i],
                gameStateDataSource.drawP2Card()
            )
        }
    }

}