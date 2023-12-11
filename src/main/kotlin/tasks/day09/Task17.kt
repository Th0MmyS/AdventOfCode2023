package tasks.day09

import AbstractTask

class Task17 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Long>() {

    override fun calculate(): Long {
        val pyramids = inputData.map { line ->
            val numbers = line.split(" ").map { it.toLong() }
            Pyramid(mutableListOf(Level(numbers)))
        }

        pyramids.forEach { it.generateLevels() }
        val numbers = pyramids.map { it.calculateNextNumberInFirstLevel() }

        return numbers.sum()
    }
}

data class Pyramid constructor(
    val levels: MutableList<Level>
) {
    fun calculateNextNumberInFirstLevel(): Long {
        var result = levels.last().bricks.last()
        levels.reversed().drop(1).forEach {
            val n = it.bricks.last()
            result += n
        }
        return result
    }

    fun generateLevels() {
        while (!levels.last().containsAllZeroes()) {
            val newLevel = mutableListOf<Long>()
            val lastLayerBricks = levels.last().bricks
            lastLayerBricks.forEachIndexed { index, brick ->
                if (index + 1 < lastLayerBricks.size) {
                    newLevel.add(lastLayerBricks[index + 1] - brick)
                }
            }

            levels.add(Level(newLevel))
        }
    }
}

data class Level constructor(
    val bricks: List<Long>
) {
    fun containsAllZeroes() = bricks.all { it == 0L }
}