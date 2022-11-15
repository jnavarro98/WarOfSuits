package com.janavarro.war_of_suits.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.janavarro.war_of_suits.components.pokerCard.PokerCardView
import com.janavarro.war_of_suits.databinding.ActivityGameBinding
import com.janavarro.war_of_suits.utils.Hearts
import com.janavarro.war_of_suits.utils.PokerValue


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val gameActivityViewModel by viewModels<GameViewModel> {
        GameViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        startGameLogic()
        initUi()
    }

    private fun startGameLogic() {
        val card = PokerCardView(this)
        card.suit = Hearts(2)
        card.pokerValue = PokerValue.Q
    }

    private fun initUi() {
        initButtons()
    }

    private fun initButtons() {
        binding.btDrawCardP2.setOnClickListener {
            binding.cardContainerP2.removeAllViews()
            gameActivityViewModel.deckLiveData.observe(this) {
                val card = it.removeLast()
                val cardView = PokerCardView(this)
                cardView.suit = card.suit
                cardView.pokerValue = card.pokerValue
                binding.cardContainerP2.addView(cardView)
            }
        }
        binding.btDrawCardP1.setOnClickListener {
            binding.cardContainerP1.removeAllViews()
            gameActivityViewModel.deckLiveData.observe(this) {
                val card = it.removeLast()
                val cardView = PokerCardView(this)
                cardView.suit = card.suit
                cardView.pokerValue = card.pokerValue
                binding.cardContainerP1.addView(cardView)
            }
        }
    }
}