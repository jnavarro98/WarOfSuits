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
                showMatchFinishedDialog(gameWinner)
            }
        }
    }

    private fun initGameStateObserver() {
        gameActivityViewModel.gameCurrentStateLiveData.observe(this) { gameCurrentState ->
            if (gameCurrentState == GameCurrentState.TurnFinished) {
                gameActivityViewModel.startNewTurn()
            } else if (gameCurrentState == GameCurrentState.Finished) {
                finishGame()
            }
        }
    }

    private fun initScoreObservers() {
        gameActivityViewModel.scoreP1LiveData.observe(this) { points ->
            binding.scoreP1.text = points.toString()
            if (points > 0 && gameActivityViewModel.gameWinnerLiveData.value == Winner.Unset)
                showPlayerWinsTurnDialog(points, getString(R.string.title_p1_turn))
        }
        gameActivityViewModel.scoreP2LiveData.observe(this) { points ->
            binding.scoreP2.text = points.toString()
            if (points > 0 && gameActivityViewModel.gameWinnerLiveData.value == Winner.Unset)
                showPlayerWinsTurnDialog(points, getString(R.string.title_p2_turn))
        }
    }


    private fun initButtons() {
        binding.btDrawCardP1.setOnClickListener {
            drawP1Card()
        }

        binding.btDrawCardP2.setOnClickListener {
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

    private fun showMatchFinishedDialog(winner: Winner) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_match_result))
        when (winner) {
            Winner.Player1 -> builder.setMessage(getString(R.string.p1_win_message))
            Winner.Player2 -> builder.setMessage(getString(R.string.p2_win_message))
            Winner.Tie -> builder.setMessage(getString(R.string.tie_message))
            else -> throw Exception()
        }
        builder.setPositiveButton(getString(R.string.bt_finish_game)) { _, _ ->
            exitGame()
        }
        builder.setOnDismissListener {
            exitGame()
        }
        builder.show()
    }

    private fun exitGame() {
        gameActivityViewModel.resetGameState()
        finish()
    }

    private fun showPlayerWinsTurnDialog(currentPoints: Int, title: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(
            String.format(
                getString(R.string.subtitle_cureent_points),
                currentPoints
            )
        )
        builder.setPositiveButton(getString(R.string.ok_caps)) { _, _ -> finishTurn() }
        builder.setOnDismissListener { finishTurn() }
        builder.show()
    }

    private fun drawP1Card() {
        val drawnCard = gameActivityViewModel.drawP1Card()
        if (drawnCard != null) {
            val cardView = PokerCardView(this).apply {
                suit = drawnCard.suit
                pokerValue = drawnCard.pokerValue
            }
            binding.cardContainerP1.removeAllViews()
            binding.cardContainerP1.addView(cardView)
            binding.btDrawCardP1.isEnabled = false
        } else {
            throw Exception()
        }
    }

    private fun drawP2Card() {
        val drawnCard = gameActivityViewModel.drawP2Card()
        if (drawnCard != null) {
            val cardView = PokerCardView(this).apply {
                suit = drawnCard.suit
                pokerValue = drawnCard.pokerValue
            }
            binding.cardContainerP2.removeAllViews()
            binding.cardContainerP2.addView(cardView)
            binding.btDrawCardP2.isEnabled = false
        } else {
            //Game finished
            throw Exception()
        }
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

    override fun onBackPressed() {  /*Blocks user back button, there's UI for this*/ }
}