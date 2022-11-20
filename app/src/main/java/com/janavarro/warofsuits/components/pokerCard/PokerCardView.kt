package com.janavarro.warofsuits.components.pokerCard

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.databinding.ViewCardBinding
import com.janavarro.warofsuits.utils.EmptySuit
import com.janavarro.warofsuits.utils.PokerValue
import com.janavarro.warofsuits.utils.Suit

/*
* This component is designed to automatically draw the border of a poker card
* once you pass the Card object values
* */
class PokerCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    //Using view binding instead of data binding because is easier to customize this way
    private val binding = ViewCardBinding.inflate(LayoutInflater.from(context), this, true)

    var suit: Suit = EmptySuit
        set(imageId) {
            field = imageId
            binding.cardContent.suit = suit
            drawStars()
        }

    var pokerValue: PokerValue? = null
        set(newPokerValue) {
            field = newPokerValue
            newPokerValue?.let {
                binding.topValue.text = newPokerValue.symbol
                binding.bottomValue.text = newPokerValue.symbol
                binding.cardContent.value = newPokerValue
            }
        }

    private fun drawStars() {
        when (suit.score) {
            0 -> {
                binding.topStar.visibility = VISIBLE
            }
            1 -> {
                binding.leftStar.visibility = VISIBLE
                binding.rightStar.visibility = VISIBLE
            }
            2 -> {
                binding.topStar.visibility = VISIBLE
                binding.leftStar.visibility = VISIBLE
                binding.rightStar.visibility = VISIBLE
            }
            3 -> {
                binding.topStar.visibility = VISIBLE
                binding.bottomStar.visibility = VISIBLE
                binding.leftStar.visibility = VISIBLE
                binding.rightStar.visibility = VISIBLE
            }
        }
    }

    //Animation of drawing a card
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if(visibility == VISIBLE) {
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.card_container_animation
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(changedView.parent)
            val flipInAnimationSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.card_animation
                ) as AnimatorSet
            flipInAnimationSet.setTarget(changedView)
            flipOutAnimatorSet.start()
            flipInAnimationSet.start()
        }
    }
}