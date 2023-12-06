package tasks.day01

import AbstractTask

class Task2 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    private val numbers = listOf(
        "one", "two", "three",
        "four", "five", "six",
        "seven", "eight", "nine"
    )

    override fun calculate(): Int {
        val list = mutableListOf<Int>()

        inputData.forEach {
            var temp = it
            numbers.forEachIndexed { index, text ->
                temp = temp.replace(text, text + (index + 1).toString() + text)
            }

            val firstDigit = temp.first { a -> a.isDigit() }
            val lastDigit = temp.last { a -> a.isDigit() }

            list.add("$firstDigit$lastDigit".toInt())
        }

        return list.sum()
    }
}