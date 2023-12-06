package tasks.day03

import AbstractTask
import log

private val bannedSymbols = listOf('-', '*', '&', '$', '@', '/', '#', '=', '%', '+')

class Task06 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    private fun hasSymbol(char: Char?): Boolean {
        char ?: return false
        return bannedSymbols.contains(char)
    }

    override fun calculate(): Int {
        val rowSize = inputData.first().length
        val oneLine = inputData.joinToString("")
        val numbers = mutableListOf<Int>()

        var currentNumber = ""
        var caughtSymbol = false
        var caughtGear = 0

        val map = mutableListOf<KeyValue>()

        oneLine.forEachIndexed { index, letter ->
            if (letter.isDigit()) {
                currentNumber += letter

                if (((index - 1) % rowSize != rowSize - 1) && hasSymbol(oneLine.getOrNull(index - 1))) {
                    //left
                    caughtSymbol = true
                    if (oneLine.getOrNull(index - 1) == '*') {
                        caughtGear = index - 1
                    }
                }

                if (((index + 1) % rowSize != 0) && hasSymbol(oneLine.getOrNull(index + 1))) {
                    // right
                    caughtSymbol = true
                    if (oneLine.getOrNull(index + 1) == '*') {
                        caughtGear = index + 1
                    }
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize))) {
                    // above
                    caughtSymbol = true
                    if (oneLine.getOrNull(index - rowSize) == '*') {
                        caughtGear = index - rowSize
                    }
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize))) {
                    // below
                    caughtSymbol = true
                    if (oneLine.getOrNull(index + rowSize) == '*') {
                        caughtGear = index + rowSize
                    }
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize - 1)) && (index - rowSize - 1) % rowSize != rowSize - 1) {
                    //top left
                    caughtSymbol = true
                    if (oneLine.getOrNull(index - rowSize - 1) == '*') {
                        caughtGear = index - rowSize - 1
                    }
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize - 1)) && (index + rowSize - 1) % rowSize != rowSize - 1) {
                    // bottom left
                    caughtSymbol = true
                    if (oneLine.getOrNull(index + rowSize - 1) == '*') {
                        caughtGear = index + rowSize - 1
                    }
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize + 1)) && (index - rowSize + 1) % rowSize != 0) {
                    // top right
                    caughtSymbol = true
                    if (oneLine.getOrNull(index - rowSize + 1) == '*') {
                        caughtGear = index - rowSize + 1
                    }
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize + 1)) && (index + rowSize + 1) % rowSize != 0) {
                    // bottom right
                    caughtSymbol = true
                    if (oneLine.getOrNull(index + rowSize + 1) == '*') {
                        caughtGear = index + rowSize + 1
                    }
                }
            } else {
                if (caughtSymbol) {
                    numbers.add(currentNumber.toInt())
                }
                if (caughtGear != 0) {
                    map.add(KeyValue(currentNumber.toInt(), caughtGear))
                }
                caughtGear = 0
                caughtSymbol = false
                currentNumber = ""
            }
        }

        val result = mutableSetOf<Int>()
        val gears = map.map { it.gear }

        gears.forEach {
            map.filter { a -> a.gear == it }
                .takeIf { it.size > 1 }
                ?.reduce { acc, keyValue -> KeyValue(acc.value * keyValue.value, it) }
                ?.let { result.add(it.value) }
        }

        return result.sum()
    }
}

data class KeyValue constructor(
    val value: Int,
    val gear: Int,
)