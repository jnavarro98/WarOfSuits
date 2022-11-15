package com.janavarro.war_of_suits.ui.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.war_of_suits.data.DecksDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.model.Decks
import com.janavarro.war_of_suits.utils.*

class GameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(
                decksDataSource = DecksDataSource.getGameDataSource(
                    generateDecks()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun generateDecks(): Decks {
        val deck = mutableListOf<Card>()
        val priorities = getSuitPriority()
        PokerValue.values().forEach { value ->
            deck.add(Card(value, Hearts(priorities[0])))
            deck.add(Card(value, Clubs(priorities[1])))
            deck.add(Card(value, Spades(priorities[2])))
            deck.add(Card(value, Diamonds(priorities[3])))
        }
        val decks = deck.shuffled().chunked(26)
        return Decks(
            ArrayDeque(decks[0]),
            ArrayDeque(decks[1])
        )
    }

    private fun getSuitPriority() = listOf(0, 1, 2, 3).shuffled()
}