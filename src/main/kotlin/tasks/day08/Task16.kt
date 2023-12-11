package tasks.day08

import AbstractTask

class Task16 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Long>() {

    override fun calculate(): Long {
        val directions = inputData.first().map {
            if (it == 'L') Direction.LEFT else Direction.RIGHT
        }

        val points = inputData.drop(2).map {
            val split = it.split("=")
            val name = split.first().replace(" ", "")
            val d = split.last().split(",")
            val left = d.first().replace(" ", "").replace("(", "")
            val right = d.last().replace(" ", "").replace(")", "")
            Point(name, left, right)
        }.associateBy { it.name }

        val currents = points.values
            .filter { it.name.endsWith('A') }
            .toTypedArray()

        var steps = 0L
        var direction: Direction

        while (!currents.all { it.name.endsWith('Z') }) {
            direction = directions[(steps % directions.size).toInt()]

            currents.forEachIndexed { index, point ->
                currents[index] = when (direction) {
                    Direction.LEFT -> points[point.left] ?: return -1
                    Direction.RIGHT -> points[point.right] ?: return -1
                }
            }

            steps++
            if (steps % 10_000_000L == 0L) {
                println(steps)
                println("---")
            }
        }

        // more than 12_840_000_000

        return steps
    }
}