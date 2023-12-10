package tasks.day07

import AbstractTask

private val allCards = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')

class Task13 constructor(
    override var inputData: List<String>,
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val hands = inputData.map {
            val split = it.split(" ")
            Hand(
                cards = split.first(),
                value = split.last().toInt()
            )
        }

        return hands.sortedWith { hand1, hand2 -> hand1.compareTo(hand2) }
            .mapIndexed { index, hand -> hand.value * (hands.size - index) }
            .sum()
    }
}

data class Hand constructor(
    val cards: String,
    val value: Int,
) {
    private lateinit var combination: Combination

    init {
        getCombination()
    }

    private fun getCombination() {
        val counts = allCards.map { a -> cards.count { it == a } }
        combination = when {
            counts.any { it == 5 } -> Combination.FIVE_OF_A_KIND
            counts.any { it == 4 } -> Combination.FOUR_OF_A_KIND
            counts.contains(3) && counts.contains(2) -> Combination.FULL_HOUSE
            counts.any { it == 3 } -> Combination.THREE_OF_A_KIND
            counts.count { it == 2 } == 2 -> Combination.TWO_PAIRS
            counts.any { it == 2 } -> Combination.ONE_PAIR
            else -> Combination.HIGH_CARD
        }
    }

    fun compareTo(hand: Hand): Int {
        val result = this.combination.compareTo(hand.combination)
        if (result != 0) return result

        var index = 0
        while (allCards.indexOf(this.cards[index]) == allCards.indexOf(hand.cards[index])) {
            index++
        }

        return allCards.indexOf(this.cards[index])
            .compareTo(allCards.indexOf(hand.cards[index]))
    }

    enum class Combination {
        FIVE_OF_A_KIND,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        THREE_OF_A_KIND,
        TWO_PAIRS,
        ONE_PAIR,
        HIGH_CARD,
    }
}