package com.janavarro.war_of_suits.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.janavarro.war_of_suits.components.pokerCard.PokerCardView
import com.janavarro.war_of_suits.databinding.ActivityGameBinding
import com.janavarro.war_of_suits.model.Card
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
        binding.btDrawCardP1.setOnClickListener {
            var currentCard: Card? = null
            gameActivityViewModel.gameStateLiveData.observe(this) {
                currentCard = it.P1Deck.removeLastOrNull()
            }
            drawP1Card(currentCard)
        }

        binding.btDrawCardP2.setOnClickListener {
            var currentCard: Card? = null
            gameActivityViewModel.gameStateLiveData.observe(this) {
                currentCard = it.P2Deck.removeLastOrNull()
            }
            drawP2Card(currentCard)
        }
    }

    private fun drawP1Card(drawnCard: Card?) {
        if (drawnCard != null) {
            gameActivityViewModel.setP1Card(drawnCard)
            val cardView = PokerCardView(this).apply {
                suit = drawnCard.suit
                pokerValue = drawnCard.pokerValue
            }
            binding.cardContainerP1.removeAllViews()
            binding.cardContainerP1.addView(cardView)
        } else {
            //Game finished
        }
    }

    private fun drawP2Card(drawnCard: Card?) {
        if (drawnCard != null) {
            gameActivityViewModel.setP2Card(drawnCard)
            val cardView = PokerCardView(this).apply {
                suit = drawnCard.suit
                pokerValue = drawnCard.pokerValue
            }
            binding.cardContainerP2.removeAllViews()
            binding.cardContainerP2.addView(cardView)
        } else {
            //Game finished
        }
    }
}