package com.janavarro.war_of_suits

import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.ui.game.GameViewModel
import com.janavarro.war_of_suits.utils.GameConstants.SCORE_INCREASE
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Mock
    private lateinit var gameStateDataSource: GameStateDataSource

    @Before
    fun setup() {
        gameStateDataSource = mock(GameStateDataSource::class.java)
        viewModel = GameViewModel(gameStateDataSource)
    }

    @Test
    fun p1ScoreAdded() {
        gameStateDataSource.addP1Score()
        assertEquals(gameStateDataSource.scoreP1.value, SCORE_INCREASE)
    }

    @Test
    fun p2ScoreAdded() {
        gameStateDataSource.addP2Score()
        assertEquals(gameStateDataSource.scoreP2.value, SCORE_INCREASE)
    }

    @Test
    fun turnHasStarted() {
        gameStateDataSource.addP2Score()
        assertEquals(gameStateDataSource.scoreP2.value, SCORE_INCREASE)
    }
}