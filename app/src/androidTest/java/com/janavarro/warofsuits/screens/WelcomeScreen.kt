package com.janavarro.warofsuits.screens

import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.ui.welcome.WelcomeActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

// Idem GameScreen
object WelcomeScreen : KScreen<WelcomeScreen>() {

    override val layoutId = R.layout.activity_welcome
    override val viewClass = WelcomeActivity::class.java

    private val appTitle = KTextView { withId(R.id.tv_title) }
    private val playButton = KTextView { withText(R.string.play) }

    fun screenIsDisplayed() {
        appTitle.isDisplayed()
    }

    fun clickPlayButton() {
        playButton.perform { click() }
    }

}