package tasks.day08

import AbstractTask

class Task16 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Long>() {

    override fun calculate(): Long {
        val directions = inputData.first().map {
            if (it == 'L') Direction.LEFT else Direction.RIGHT
        }

        val allPoints = inputData.drop(2).map {
            val split = it.split("=")
            val name = split.first().replace(" ", "")
            val d = split.last().split(",")
            val left = d.first().replace(" ", "").replace("(", "")
            val right = d.last().replace(" ", "").replace(")", "")
            Point(name, left, right)
        }.associateBy { it.name }

        val startingPoints = allPoints.values
            .filter { it.name.endsWith('A') }
            .toTypedArray()

        var steps = 0L
        var direction: Direction

        val cycles = mutableListOf<Long>()

        startingPoints.forEach {
            var currentPoint = it
            var firstStep = 0L
            while (true) {
                direction = directions[(steps % directions.size).toInt()]

                currentPoint = when (direction) {
                    Direction.LEFT -> allPoints[currentPoint.left] ?: return -1
                    Direction.RIGHT -> allPoints[currentPoint.right] ?: return -1
                }

                steps++

                if (currentPoint.name.endsWith('Z')) {
                    if (firstStep == 0L) {
                        // save first appearance of Z
                        firstStep = steps
                    } else {
                        // second appearance of Z, save the difference (cycle size)
                        cycles.add(steps - firstStep)
                        break
                    }
                }
            }
        }

        return lcm(cycles)
    }

    private fun lcm(numbers: List<Long>) =
        numbers.fold(1L) { a, b -> a * (b / gcf(a, b)) }

    private fun gcf(number1: Long, number2: Long): Long {
        var a = number1
        var b = number2

        while (b != 0L) {
            val temp = b
            b = a % b
            a = temp
        }

        return a
    }
}