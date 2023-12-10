package tasks.day07

private val allCards = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
private val allCardsWithJoker = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')

data class Hand constructor(
    val cards: String,
    val value: Int,
) {

    fun compareTo(hand: Hand): Int {
        val result = this.getCombination(this.cards)
            .compareTo(hand.getCombination(hand.cards))
        if (result != 0) return result

        var index = 0
        while (allCards.indexOf(this.cards[index]) == allCards.indexOf(hand.cards[index])) {
            index++
        }

        return allCards.indexOf(this.cards[index])
            .compareTo(allCards.indexOf(hand.cards[index]))
    }

    private fun getCombination(cards: String): Combination {
        val counts = allCards.map { a -> cards.count { it == a } }
        return when {
            counts.any { it == 5 } -> Combination.FIVE_OF_A_KIND
            counts.any { it == 4 } -> Combination.FOUR_OF_A_KIND
            counts.contains(3) && counts.contains(2) -> Combination.FULL_HOUSE
            counts.any { it == 3 } -> Combination.THREE_OF_A_KIND
            counts.count { it == 2 } == 2 -> Combination.TWO_PAIRS
            counts.any { it == 2 } -> Combination.ONE_PAIR
            else -> Combination.HIGH_CARD
        }
    }

    private fun getMaxCombination() = allCardsWithJoker.minOf {
        getCombination(cards.replace('J', it))
    }

    fun compareToMaxCombination(hand: Hand): Int {
        val result = this.getMaxCombination().compareTo(hand.getMaxCombination())
        if (result != 0) return result

        var index = 0
        while (allCardsWithJoker.indexOf(this.cards[index]) == allCardsWithJoker.indexOf(hand.cards[index])
        ) {
            index++
        }

        return allCardsWithJoker.indexOf(this.cards[index])
            .compareTo(allCardsWithJoker.indexOf(hand.cards[index]))
    }
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