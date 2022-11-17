package com.janavarro.war_of_suits.ui.welcome

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.janavarro.war_of_suits.data.IconButtonDataSource
import com.janavarro.war_of_suits.model.IconButton


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