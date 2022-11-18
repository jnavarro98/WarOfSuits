package com.janavarro.war_of_suits

import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.ui.game.GameViewModel
import com.janavarro.war_of_suits.utils.Clubs
import com.janavarro.war_of_suits.utils.GameConstants.SCORE_INCREASE
import com.janavarro.war_of_suits.utils.PokerValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

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
    fun cardP1ValueIsHigher() {
        viewModel.cardP1 = Card(PokerValue.A, Clubs(0))
        viewModel.cardP1 = Card(PokerValue.Ten, Clubs(0))
        viewModel.checkGameState()
        verify(gameStateDataSource.addP1Score())
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