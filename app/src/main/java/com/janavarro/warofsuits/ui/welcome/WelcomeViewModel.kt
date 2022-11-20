package com.janavarro.warofsuits.ui.welcome

import androidx.lifecycle.ViewModel
import com.janavarro.warofsuits.data.IconButtonDataSource

//This screen has no logic but thanks to the architecture it is easy to implement some
class WelcomeViewModel(val iconButtonDataSource: IconButtonDataSource) : ViewModel() {

    val iconButtonLiveData = iconButtonDataSource.getIconButtonList()

}