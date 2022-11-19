package com.janavarro.war_of_suits.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

data class IconButton(
    val title: String,
    @DrawableRes val image: Int,
    val activityToLaunch: Activity,
    val uri : Uri = Uri.EMPTY
)

fun IconButton.launch(context: Context) {
    val intent = Intent(context, activityToLaunch::class.java)
    ContextCompat.startActivity(context, intent, null)
}
