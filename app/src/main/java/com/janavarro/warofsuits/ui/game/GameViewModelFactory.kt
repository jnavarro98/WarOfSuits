package com.janavarro.warofsuits.ui.game

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.warofsuits.data.GameStateDataSource
import com.janavarro.warofsuits.model.GameState


//Injects dependencies to view model
//TODO: In the future, we can use context to get saved preferences (Game save etc...)
class GameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(
                GameStateDataSource.getGameDataSource(GameState())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}