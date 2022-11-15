package com.janavarro.war_of_suits.ui.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.war_of_suits.data.DeckDataSource
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.utils.PokerCardUtils

class GameViewModel(val deckDataSource: DeckDataSource) : ViewModel() {

    val deckLiveData = deckDataSource.getDeckList()

    //This will be used in the future
    fun insertCard(
        number: Int,
        suit: Int
    ) {
        val newCard = Card(
            number,
            suit
        )

        deckDataSource.addCard(newCard)
    }
}

class GameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    //In the future we can retrieve data from a WS (Firebase for example)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(
                deckDataSource = DeckDataSource.getDeckDataSource(
                    listOf(
                        Card(
                            0,
                            PokerCardUtils.Clubs().image
                        )
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}