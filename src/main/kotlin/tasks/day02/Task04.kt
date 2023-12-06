package tasks.day02

import AbstractTask

class Task04 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val games = parseGames(inputData)

        val scores = mutableListOf<Int>()

        games.forEach {
            var minB = 0
            var minG = 0
            var minR = 0

            it.hands.forEach { hand ->
                val minBlue = getMaxValue(hand, Color.BLUE)
                if (minB < minBlue) {
                    minB = minBlue
                }

                val minRed = getMaxValue(hand, Color.RED)
                if (minR < minRed) {
                    minR = minRed
                }

                val minGreen = getMaxValue(hand, Color.GREEN)
                if (minG < minGreen) {
                    minG = minGreen
                }
            }

            scores.add(minB * minR * minG)
        }

        return scores.sum()
    }

    private fun getMaxValue(hand: Hand, color: Color) = hand.dice
        .filter { a -> a.color == color }
        .takeIf { a -> a.isNotEmpty() }
        ?.maxOf { a -> a.number } ?: 0
}