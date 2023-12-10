package tasks.day07

import AbstractTask

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