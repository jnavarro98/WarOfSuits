package com.janavarro.warofsuits.ui.welcome

import androidx.lifecycle.ViewModel
import com.janavarro.warofsuits.data.IconButtonDataSource

//Not much logic in this screen
class WelcomeViewModel(val iconButtonDataSource: IconButtonDataSource) : ViewModel() {

    val iconButtonLiveData = iconButtonDataSource.getIconButtonList()

}