package com.janavarro.war_of_suits.screens

import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.ui.welcome.WelcomeActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object WelcomeScreen : KScreen<WelcomeScreen>() {

    override val layoutId = R.layout.activity_welcome
    override val viewClass = WelcomeActivity::class.java

    private val appTitle = KTextView { withId(R.id.tv_title) }
    private val playButton = KTextView { withText(R.string.play) }

    fun screenIsDisplayed() {
        appTitle.isDisplayed()
    }

    fun playButtonIsDisplayed() {
        playButton.isDisplayed()
    }
}