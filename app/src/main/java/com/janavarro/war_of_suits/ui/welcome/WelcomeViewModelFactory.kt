package com.janavarro.war_of_suits.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.data.IconButtonDataSource
import com.janavarro.war_of_suits.model.IconButton
import com.janavarro.war_of_suits.ui.game.GameActivity

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