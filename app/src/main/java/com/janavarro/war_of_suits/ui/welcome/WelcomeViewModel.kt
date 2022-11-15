package com.janavarro.war_of_suits.ui.welcome

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.data.IconButtonDataSource
import com.janavarro.war_of_suits.model.IconButton
import com.janavarro.war_of_suits.ui.game.GameActivity


class WelcomeViewModel(val iconButtonDataSource: IconButtonDataSource) : ViewModel() {

    val iconButtonLiveData = iconButtonDataSource.getIconButtonList()

    //This will be used in the future
    fun insertIconButton(
        iconButtonIcon: Int,
        iconButtonTitle: String,
        activityToLaunch: Activity
    ) {
        val newIconButton = IconButton(
            iconButtonTitle,
            iconButtonIcon,
            activityToLaunch
        )

        iconButtonDataSource.addIconButton(newIconButton)
    }
}

class WelcomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    //In the future we can retrieve data from a WS (Firebase for example)
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