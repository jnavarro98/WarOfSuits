package com.janavarro.war_of_suits.model

import android.app.Activity
import androidx.annotation.DrawableRes

data class IconButton(
    val title: String,
    @DrawableRes val image: Int,
    val activityToLaunch: Activity
)
