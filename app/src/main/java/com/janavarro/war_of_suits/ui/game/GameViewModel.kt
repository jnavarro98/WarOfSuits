package com.janavarro.war_of_suits.ui.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.war_of_suits.data.DeckDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.utils.*

class GameViewModel(val deckDataSource: DeckDataSource) : ViewModel() {

    val deckLiveData = deckDataSource.getDeckList()

    //This will be used in the future
    fun insertCard(
        pokerValue: PokerValue,
        suit: Suit
    ) {
        val newCard = Card(
            pokerValue,
            suit
        )

        deckDataSource.addCard(newCard)
    }
}

class GameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(
                deckDataSource = DeckDataSource.getDeckDataSource(
                    generateDeck()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun generateDeck(): ArrayDeque<Card> {
        val deck = mutableListOf<Card>()
        val priorities = getSuitPriority()
        PokerValue.values().forEach { value ->
            deck.add(Card(value, Hearts(priorities[0])))
            deck.add(Card(value, Clubs(priorities[1])))
            deck.add(Card(value, Spades(priorities[2])))
            deck.add(Card(value, Diamonds(priorities[3])))
        }
        return ArrayDeque(deck)
    }

    private fun getSuitPriority() = listOf(0, 1, 2, 3).shuffled()
}