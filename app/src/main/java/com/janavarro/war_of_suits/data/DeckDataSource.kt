package com.janavarro.war_of_suits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.model.Card

/* Handles operations on iconButtonsLiveData and holds details about it. */
class DeckDataSource(deck: List<Card>) {
    private val initialDeck = deck
    private val deckLiveData = MutableLiveData(initialDeck)

    /* Adds iconButton to liveData and posts value. */
    fun addCard(card: Card) {
        val currentList = deckLiveData.value
        if (currentList == null) {
            deckLiveData.postValue(listOf(card))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, card)
            deckLiveData.postValue(updatedList)
        }
    }

    /* Removes iconButton from liveData and posts value. */
    fun removeCard(card: Card) {
        val currentList = deckLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(card)
            deckLiveData.postValue(updatedList)
        }
    }

    //TODO: Maybe is useful in the future

    /* Returns iconButton given an ID.
    fun getIconButtonForId(id: Long): IconButton? {
        iconButtonLiveData.value?.let { iconButtons ->
            return deck.firstOrNull{ it.id == id}
        }
        return null
    } */

    fun getDeckList(): LiveData<List<Card>> {
        return deckLiveData
    }

    companion object {
        private var INSTANCE: DeckDataSource? = null

        fun getDeckDataSource(deck: List<Card>): DeckDataSource {
            return synchronized(DeckDataSource::class) {
                val newInstance = INSTANCE ?: DeckDataSource(deck)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}