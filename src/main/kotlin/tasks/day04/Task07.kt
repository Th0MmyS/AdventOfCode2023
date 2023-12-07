package tasks.day04

import AbstractTask
import kotlin.math.pow
import kotlin.random.Random

class Task07 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val cards = mutableListOf<Card>()

        inputData.forEach {
            val s1 = it.split(":")
            val s2 = s1.last().split("|")

            val left = s2.first()
                .split(" ")
                .mapNotNull { a -> a.replace(" ", "").toShortOrNull() }
            val right = s2.last()
                .split(" ")
                .mapNotNull { a -> a.replace(" ", "").toShortOrNull() }

            cards.add(Card(left, right))
        }

        return cards.sumOf {
            var index = 0
            it.left.forEach { c ->
                if (it.right.contains(c)) {
                    index++
                }
            }

            return@sumOf 2.0.pow(index - 1).toInt()
        }
    }
}

data class Card constructor(
    val id: Short,
    val left: List<Short>,
    val right: List<Short>
) {
    constructor(left: List<Short>, right: List<Short>) : this(
        Random.nextInt().toShort(),
        left,
        right
    )

    override fun toString(): String {
        return "Card(id=$id)"
    }
}