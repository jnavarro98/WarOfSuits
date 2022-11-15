package com.janavarro.war_of_suits.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janavarro.war_of_suits.model.Decks

/* Handles operations on iconButtonsLiveData and holds details about it. */
class DecksDataSource(decks: Decks) {
    private val initialDecks = decks
    private val decksLiveData = MutableLiveData(initialDecks)

    fun getDeckList(): LiveData<Decks> {
        return decksLiveData
    }

    companion object {
        private var INSTANCE: DecksDataSource? = null

        fun getGameDataSource(decks: Decks): DecksDataSource {
            return synchronized(DecksDataSource::class) {
                val newInstance = INSTANCE ?: DecksDataSource(decks)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}