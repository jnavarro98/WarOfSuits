package com.janavarro.warofsuits.test.screens

import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.ui.game.GameActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object GameScreen : KScreen<GameScreen>() {

    override val layoutId = R.layout.activity_game
    override val viewClass = GameActivity::class.java

    private val p1Title = KTextView { withText(R.string.p1_label) }

    fun screenIsDisplayed() {
        p1Title.isDisplayed()
    }
}