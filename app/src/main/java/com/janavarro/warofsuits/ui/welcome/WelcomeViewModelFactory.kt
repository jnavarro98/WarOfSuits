package com.janavarro.warofsuits.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.data.IconButtonDataSource
import com.janavarro.warofsuits.model.IconButton
import com.janavarro.warofsuits.ui.game.GameActivity

//TODO: In the future we can show more buttons depending on saved preferences.
class WelcomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WelcomeViewModel(
                iconButtonDataSource = IconButtonDataSource.getIconButtonDataSource(
                    listOf(
                        IconButton(
                            context.getString(R.string.play),
                            R.drawable.ic_baseline_play_arrow,
                            GameActivity()
                        )
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}