package com.janavarro.war_of_suits.ui.game

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.janavarro.war_of_suits.components.pokerCard.PokerCardView
import com.janavarro.war_of_suits.databinding.ActivityGameBinding
import com.janavarro.war_of_suits.utils.PokerCardUtils


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val gameActivityViewModel by viewModels<GameViewModel> {
        GameViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        val card = PokerCardView(this)
        card.suit = PokerCardUtils.Spade().image
        card.value = PokerCardUtils.K()
        binding.cardContainer.addView(card)
    }
}