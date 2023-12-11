package tasks.day09

import AbstractTask

class Task18 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Long>() {

    override fun calculate(): Long {
        val pyramids = inputData.map { line ->
            val numbers = line.split(" ").map { it.toLong() }
            Pyramid(mutableListOf(Level(numbers)))
        }

        return pyramids.sumOf {
            it.generateLevels()
            it.calculatePreviousNumberInFirstLevel()
        }
    }
}