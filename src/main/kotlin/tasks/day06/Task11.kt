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

        return List(times.size) { index: Int ->
            var win = 0
            repeat(times[index]) { waitTime ->
                if (waitTime * (times[index] - waitTime) > distances[index]) {
                    win++
                }
            }
            win
        }.reduce { a, b -> a * b }
    }
}