package tasks.day08

import AbstractTask
import tasks.day08.Direction.LEFT
import tasks.day08.Direction.RIGHT

private const val START = "AAA"
private const val END = "ZZZ"

class Task15 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val directions = inputData.first().map {
            if (it == 'L') LEFT else RIGHT
        }

        val points = inputData.drop(2).map {
            val split = it.split("=")
            val name = split.first().replace(" ", "")
            val d = split.last().split(",")
            val left = d.first().replace(" ", "").replace("(", "")
            val right = d.last().replace(" ", "").replace(")", "")
            Point(name, left, right)
        }.associateBy { it.name }

        var current = points[START]

        var steps = 0
        var direction: Direction
        while (current?.name != END) {
            direction = directions[steps % directions.size]
            current = when (direction) {
                LEFT -> points[current?.left]
                RIGHT -> points[current?.right]
            }

            steps++
        }
        return steps
    }
}

data class Point constructor(
    val name: String,
    val left: String,
    val right: String,
)

enum class Direction {
    LEFT, RIGHT,
}