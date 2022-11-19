package com.janavarro.war_of_suits.ui.welcome

import androidx.lifecycle.ViewModel
import com.janavarro.war_of_suits.data.IconButtonDataSource

class WelcomeViewModel(val iconButtonDataSource: IconButtonDataSource) : ViewModel() {

    val iconButtonLiveData = iconButtonDataSource.getIconButtonList()

}