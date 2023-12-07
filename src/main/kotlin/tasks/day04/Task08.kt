package tasks.day04

import AbstractTask

class Task08 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val cards = mutableListOf<Card>()

        inputData.forEach {
            val s1 = it.split(":")
            val s2 = s1.last().split("|")

            val cardId = s1.first().split(" ").last().toShort()

            val left = s2.first()
                .split(" ")
                .mapNotNull { a -> a.replace(" ", "").toShortOrNull() }
            val right = s2.last()
                .split(" ")
                .mapNotNull { a -> a.replace(" ", "").toShortOrNull() }

            cards.add(Card(cardId, left, right))
        }

        val hashMap = generateHashMap(cards)
        return cards.size + cards.sumOf { getWinningCards(it, hashMap) }
    }

    private fun generateHashMap(
        cards: MutableList<Card>
    ): HashMap<Card, List<Card>> {
        val map = hashMapOf<Card, List<Card>>()
        var list = mutableListOf<Card>()

        cards.forEach { card ->
            card.left.forEach { leftNumber ->
                if (card.right.contains(leftNumber)) {
                    list.add(cards[cards.indexOf(card) + list.size + 1])
                }
            }
            map[card] = list
            list = mutableListOf()
        }

        return map
    }

    private fun getWinningCards(
        card: Card,
        cards: HashMap<Card, List<Card>>
    ): Int {
        var count = 0
        card.left.forEach { leftNumber ->
            if (card.right.contains(leftNumber)) {
                count++
            }
        }

        return count + cards[card].orEmpty().sumOf {
            getWinningCards(it, cards)
        }
    }
}