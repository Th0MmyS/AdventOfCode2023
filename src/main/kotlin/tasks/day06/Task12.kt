package tasks.day06

import AbstractTask

class Task12 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val runtime = inputData[0].split(" ")
            .filter { a -> a.isNotBlank() }
            .drop(1)
            .joinToString("")
            .toLong()

        val distanceToBeat = inputData[1].split(" ")
            .filter { a -> a.isNotBlank() }
            .drop(1)
            .joinToString("")
            .toLong()

        return LongRange(0, runtime).count { waitTime ->
            waitTime * (runtime - waitTime) > distanceToBeat
        }
    }
}