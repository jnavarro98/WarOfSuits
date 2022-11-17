package com.janavarro.war_of_suits.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.janavarro.war_of_suits.R
import com.janavarro.war_of_suits.components.pokerCard.PokerCardView
import com.janavarro.war_of_suits.databinding.ActivityGameBinding
import com.janavarro.war_of_suits.utils.GameCurrentState
import com.janavarro.war_of_suits.utils.Winner


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
        initScoreObservers()
        initGameStateObserver()
        initWinConditionObserver()
    }

    private fun initWinConditionObserver() {
        gameActivityViewModel.gameWinnerLiveData.observe(this) { gameWinner ->
            if (gameWinner != Winner.Unset) {
                showWinnerDialog(gameWinner)
            }
        }
    }

    private fun showWinnerDialog(winner: Winner) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_winner))
        when (winner) {
            Winner.Player1 -> builder.setMessage(getString(R.string.p1_win_message))
            Winner.Player2 -> builder.setMessage(getString(R.string.p2_win_message))
            else -> throw Exception()
        }
        builder.setPositiveButton(getString(R.string.bt_finish_game)) { _, _ ->
            gameActivityViewModel.resetGameState()
            finish()
        }
        builder.setOnDismissListener {
            gameActivityViewModel.resetGameState()
            finish()
        }
        builder.show()
    }

    private fun initGameStateObserver() {
        gameActivityViewModel.gameCurrentStateLiveData.observe(this) { gameCurrentState ->
            if (gameCurrentState == GameCurrentState.TurnFinished) {
                gameActivityViewModel.finishTurn()
            } else if (gameCurrentState == GameCurrentState.Finished) {
                finishGame()
            }
        }
    }

    private fun initScoreObservers() {
        gameActivityViewModel.scoreP1LiveData.observe(this) { points ->
            binding.scoreP1.text = points.toString()
            if (points > 0 && gameActivityViewModel.gameWinnerLiveData.value == Winner.Unset)
                showP1WinsTurnDialog(points)
        }
        gameActivityViewModel.scoreP2LiveData.observe(this) { points ->
            binding.scoreP2.text = points.toString()
            if (points > 0 && gameActivityViewModel.gameWinnerLiveData.value == Winner.Unset)
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

    private fun finishGame() {
        binding.cardContainerP1.removeAllViews()
        binding.cardContainerP2.removeAllViews()
        binding.btDrawCardP1.isEnabled = true
        binding.btDrawCardP2.isEnabled = true
        binding.scoreP1.text = "0"
        binding.scoreP2.text = "0"
    }

    private fun initButtons() {
        binding.btDrawCardP1.setOnClickListener {
//            gameActivityViewModel.decksLiveData.observe(this) { decks ->
//                currentCard = decks.p1Deck.removeLastOrNull()
//            }
            drawP1Card()
        }

        binding.btDrawCardP2.setOnClickListener {
//            gameActivityViewModel.decksLiveData.observe(this) { decks ->
//                currentCard = decks.p2Deck.removeLastOrNull()
//            }
            drawP2Card()
        }

        binding.btRestart.setOnClickListener {
            gameActivityViewModel.resetGameState()
        }

        binding.btExit.setOnClickListener {
            gameActivityViewModel.resetGameState()
            finish()
        }
    }

    private fun drawP1Card() {
        val drawnCard = gameActivityViewModel.drawP1Card()
        if (drawnCard != null) {
            gameActivityViewModel.cardP1 = drawnCard
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

    private fun drawP2Card() {
        val drawnCard = gameActivityViewModel.drawP2Card()
        if (drawnCard != null) {
            gameActivityViewModel.cardP2 = drawnCard
            val cardView = PokerCardView(this).apply {
                suit = drawnCard.suit
                pokerValue = drawnCard.pokerValue
            }
            binding.cardContainerP2.removeAllViews()
            binding.cardContainerP2.addView(cardView)
            binding.btDrawCardP2.isEnabled = false
            gameActivityViewModel.checkGameState()
        } else {
            //Game finished
            throw Exception()
        }
    }

    override fun onBackPressed() {  /*Block user back button, there's UI for this*/
    }
}