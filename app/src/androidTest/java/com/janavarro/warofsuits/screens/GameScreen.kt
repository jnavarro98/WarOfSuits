package com.janavarro.warofsuits.screens

import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.ui.game.GameActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

//This is a screen, here you link to the components, use them and validate their behaviour
object GameScreen : KScreen<GameScreen>() {

    override val layoutId = R.layout.activity_game
    override val viewClass = GameActivity::class.java

    private val p1Title = KTextView { withText(R.string.p1_label) }
    private val p1DrawCardButton = KTextView { withId(R.id.bt_draw_card_p1) }
    private val p2DrawCardButton = KTextView { withId(R.id.bt_draw_card_p2) }

    fun screenIsDisplayed() {
        p1Title.isDisplayed()
    }

    fun drawCardP1() {
        p1DrawCardButton.click()
    }

    fun drawCardP2() {
        p2DrawCardButton.click()
    }
}