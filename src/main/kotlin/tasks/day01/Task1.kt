package tasks.day01

import AbstractTask

class Task1 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val list = mutableListOf<Int>()

        inputData.forEach {
            val firstDigit = it.first { a -> a.isDigit() }
            val lastDigit = it.last { a -> a.isDigit() }

            list.add("$firstDigit$lastDigit".toInt())
        }

        return list.sum()
    }
}