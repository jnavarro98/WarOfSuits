package com.janavarro.war_of_suits.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.components.pokerCard.PokerCardView
import com.janavarro.war_of_suits.databinding.ActivityGameBinding
import com.janavarro.war_of_suits.model.Card
import com.janavarro.war_of_suits.utils.Player


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
        initButtons()
        initScoreCounters()
        initTurnChecker()
        initWinConditionChecker()
    }

    private fun initWinConditionChecker() {
        gameActivityViewModel.gameWinner.observe(this) { gameWinner ->
            if (gameWinner != Player.Unset) {
                showWinnerDialog(gameWinner)
            }
        }
    }

    private fun showWinnerDialog(winner: Player) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_winner))
        when (winner) {
            Player.Player1 -> builder.setMessage(getString(R.string.p1_win_message))
            Player.Player2 -> builder.setMessage(getString(R.string.p2_win_message))
        }
        builder.setPositiveButton(getString(R.string.bt_finish_game)) { _, _ ->
            finish()
            gameActivityViewModel.finishGame()
        }
        builder.setOnDismissListener {
            finish()
            gameActivityViewModel.finishGame()
        }
        builder.show()
    }

    private fun initTurnChecker() {
        gameActivityViewModel.nextTurn.observe(this) { isNextTurn ->
            if (isNextTurn) {
                gameActivityViewModel.finishTurn()
            }
        }
    }

    private fun initScoreCounters() {
        binding.scoreP1.text = gameActivityViewModel.scoreP1.value.toString()
        gameActivityViewModel.scoreP1.observe(this) { points ->
            if (points > 0)
                showP1WinsTurnDialog(points)
        }
        binding.scoreP2.text = gameActivityViewModel.scoreP2.value.toString()
        gameActivityViewModel.scoreP2.observe(this) { points ->
            if (points > 0)
                showP2WinsTurnDialog(points)
        }
    }

    private fun showP1WinsTurnDialog(currentPoints: Int) {
        binding.scoreP1.text = currentPoints.toString()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_p1_turn))
        builder.setMessage(
            String.format(
                getString(R.string.subtitle_cureent_points),
                currentPoints
            )
        )
        builder.setPositiveButton(getString(R.string.ok_caps)) { _, _ ->
            finishTurn()
        }
        builder.setOnDismissListener {
            finishTurn()
        }
        builder.show()
    }

    private fun showP2WinsTurnDialog(currentPoints: Int) {
        binding.scoreP2.text = currentPoints.toString()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_p2_turn))
        builder.setMessage(
            String.format(
                getString(R.string.subtitle_cureent_points),
                currentPoints
            )
        )
        builder.setPositiveButton(getString(R.string.ok_caps)) { _, _ ->
            finishTurn()
        }
        builder.setOnDismissListener {
            finishTurn()
        }
        builder.show()
    }

    private fun finishTurn() {
        binding.cardContainerP1.removeAllViews()
        binding.cardContainerP2.removeAllViews()
        binding.btDrawCardP1.isEnabled = true
        binding.btDrawCardP2.isEnabled = true
    }

    private fun initButtons() {
        binding.btDrawCardP1.setOnClickListener {
            var currentCard: Card? = null
            gameActivityViewModel.decksLiveData.observe(this) {
                currentCard = it.P1Deck.removeLastOrNull()
            }
            drawP1Card(currentCard)
        }

        binding.btDrawCardP2.setOnClickListener {
            var currentCard: Card? = null
            gameActivityViewModel.decksLiveData.observe(this) {
                currentCard = it.P2Deck.removeLastOrNull()
            }
            drawP2Card(currentCard)
        }

        binding.btRestart.setOnClickListener {
            gameActivityViewModel.resetGameState()
        }

        binding.btExit.setOnClickListener {
            gameActivityViewModel.resetGameState()
            finish()
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
            binding.btDrawCardP1.isEnabled = false
            gameActivityViewModel.checkGameState()
        } else {
            //Game finished
            throw Exception()
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
            binding.btDrawCardP2.isEnabled = false
            gameActivityViewModel.checkGameState()
        } else {
            //Game should've finished before
            throw Exception()
        }
    }

    override fun onBackPressed() {  /*Block user back button, there's UI for this*/
    }
}