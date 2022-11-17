package com.janavarro.war_of_suits.ui.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.war_of_suits.data.GameStateDataSource
import com.janavarro.war_of_suits.utils.generateNewGame

class GameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(
                GameStateDataSource.getGameDataSource(generateNewGame())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}