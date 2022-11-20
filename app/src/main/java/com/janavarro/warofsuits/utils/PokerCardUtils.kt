package com.janavarro.warofsuits.utils

import androidx.annotation.DrawableRes
import com.janavarro.warofsuits.R
import com.janavarro.warofsuits.model.Card
import com.janavarro.warofsuits.model.Decks

//Sealed classes allow me to instantiate suits with different scores but fixed icons.
sealed class Suit(@DrawableRes val image: Int, open val score: Int = -1)
class Diamonds(override val score: Int = -1) : Suit(R.drawable.ic_diamonds, score)
class Clubs(override val score: Int = -1) : Suit(R.drawable.ic_clubs, score)
class Hearts(override val score: Int = -1) : Suit(R.drawable.ic_hearts, score)
class Spades(override val score: Int = -1) : Suit(R.drawable.ic_spades, score)
object EmptySuit : Suit(-1, -1)

enum class PokerValue(val score: Int, val symbol: String) {
    Two(0, "2"),
    Three(1, "3"),
    Four(2, "4"),
    Five(3, "5"),
    Six(4, "6"),
    Seven(5, "7"),
    Eight(6, "8"),
    Nine(7, "9"),
    Ten(8, "10"),
    J(9, "J"),
    Q(10, "Q"),
    K(11, "K"),
    A(12, "A")
}

fun Card.compareTo(card2: Card) =
    if (this.pokerValue.score.compareTo(card2.pokerValue.score) != 0)
        this.pokerValue.score.compareTo(card2.pokerValue.score)
    else
        this.suit.score.compareTo(card2.suit.score)

//Kotlin lists make this process straightforward
//I used array deque since it simulates the behaviour of a card deck
fun generateDecks(): Decks {
    val deck = mutableListOf<Card>()
    val priorities = getSuitPriority()
    PokerValue.values().forEach { value ->
        deck.add(Card(value, Hearts(priorities[0])))
        deck.add(Card(value, Clubs(priorities[1])))
        deck.add(Card(value, Spades(priorities[2])))
        deck.add(Card(value, Diamonds(priorities[3])))
    }
    val decks = deck.shuffled().chunked(26)
    return Decks(
        ArrayDeque(decks[0]),
        ArrayDeque(decks[1])
    )
}

private fun getSuitPriority() = listOf(0, 1, 2, 3).shuffled()

object PokerCardConstants {
    const val ROYAL_CARDS_SYMBOL_SIZE = 50f
}