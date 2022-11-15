package com.janavarro.war_of_suits.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toBitmap
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.model.IconButton


fun IconButton.launch(context: Context) {
    val intent = Intent(context, activityToLaunch::class.java)
    startActivity(context, intent, null)
}

//TODO: May be useful, change Uri.EMPTY for real uri
private fun launchCustomTab(context: Context, iconButton: IconButton) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    context.getDrawable(R.drawable.ic_baseline_arrow_back)
        ?.let { builder.setCloseButtonIcon(it.toBitmap()) }
    customTabsIntent.launchUrl(context, Uri.EMPTY)
}