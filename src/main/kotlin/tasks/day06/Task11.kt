package tasks.day06

import AbstractTask

class Task11 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val times = inputData[0].split(" ")
            .filter { a -> a.isNotBlank() }
            .drop(1)
            .map { a -> a.toInt() }

        val distances = inputData[1].split(" ")
            .filter { a -> a.isNotBlank() }
            .drop(1)
            .map { a -> a.toInt() }

        return List(times.size) { index ->
            IntRange(0, times[index]).count { waitTime ->
                waitTime * (times[index] - waitTime) > distances[index]
            }
        }.reduce { a, b -> a * b }
    }
}