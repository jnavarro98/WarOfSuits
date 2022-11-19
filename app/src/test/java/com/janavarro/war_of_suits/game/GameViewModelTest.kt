package com.janavarro.war_of_suits.game

import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.ui.game.GameViewModel
import com.janavarro.war_of_suits.utils.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

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
    fun `player 1 card has higher value`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.Ten, Hearts(1)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Two, Clubs(0)))
        viewModel.drawP1Card()
        viewModel.drawP2Card()
        verify(gameStateDataSource).addP1Score()
    }

    @Test
    fun `player 2 card has higher value`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.Two, Clubs(0)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Ten, Hearts(1)))
        viewModel.drawP1Card()
        viewModel.drawP2Card()
        verify(gameStateDataSource).addP2Score()
    }

    @Test
    fun `player 1 card has higher suit`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.Two, Hearts(1)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Two, Clubs(0)))
        viewModel.drawP1Card()
        viewModel.drawP2Card()
        verify(gameStateDataSource).addP1Score()
    }

    @Test
    fun `player 2 card has higher suit`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.Two, Clubs(0)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Two, Hearts(1)))
        viewModel.drawP1Card()
        viewModel.drawP2Card()
        verify(gameStateDataSource).addP2Score()
    }


    @Test
    fun `player 1 wins game`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.A, Clubs(0)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Two, Hearts(1)))
        `when`(gameStateDataSource.scoreP1).thenReturn(MutableLiveData(28))
        `when`(gameStateDataSource.scoreP2).thenReturn(MutableLiveData(24))
        `when`(gameStateDataSource.totalScore).thenReturn(GameConstants.MAX_SCORE)
        viewModel.drawP1Card()
        viewModel.drawP2Card()

        verify(gameStateDataSource).setWinner(Winner.Player1)
    }

    @Test
    fun `player 2 wins game`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.A, Clubs(0)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Two, Hearts(1)))
        `when`(gameStateDataSource.scoreP1).thenReturn(MutableLiveData(24))
        `when`(gameStateDataSource.scoreP2).thenReturn(MutableLiveData(28))
        `when`(gameStateDataSource.totalScore).thenReturn(GameConstants.MAX_SCORE)
        viewModel.drawP1Card()
        viewModel.drawP2Card()

        verify(gameStateDataSource).setWinner(Winner.Player2)
    }

    @Test
    fun `tie game`() {
        `when`(gameStateDataSource.drawP1Card()).thenReturn(Card(PokerValue.A, Clubs(0)))
        `when`(gameStateDataSource.drawP2Card()).thenReturn(Card(PokerValue.Two, Hearts(1)))
        `when`(gameStateDataSource.scoreP1).thenReturn(MutableLiveData(26))
        `when`(gameStateDataSource.scoreP2).thenReturn(MutableLiveData(26))
        `when`(gameStateDataSource.totalScore).thenReturn(GameConstants.MAX_SCORE)
        viewModel.drawP1Card()
        viewModel.drawP2Card()

        verify(gameStateDataSource).setWinner(Winner.Tie)
    }

    @Test
    fun `start new turn should change state`() {
        viewModel.startNewTurn()
        verify(gameStateDataSource).setGameCurrentState(GameCurrentState.Playing)
    }

    @Test
    fun `restart game should change state`() {
        viewModel.resetGameState()
        verify(gameStateDataSource).resetGameState()
    }

}