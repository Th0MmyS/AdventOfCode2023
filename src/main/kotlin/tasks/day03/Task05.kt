package tasks.day03

import AbstractTask

private val bannedSymbols = listOf('-', '*', '&', '$', '@', '/', '#', '=', '%', '+')

class Task05 constructor(
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

        oneLine.forEachIndexed { index, letter ->
            if (letter.isDigit()) {
                currentNumber += letter

                if (((index - 1) % rowSize != rowSize - 1) && hasSymbol(oneLine.getOrNull(index - 1))) {
                    //left
                    caughtSymbol = true
                }

                if (((index + 1) % rowSize != 0) && hasSymbol(oneLine.getOrNull(index + 1))) {
                    // right
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize))) {
                    // above
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize))) {
                    // below
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize - 1)) && (index - rowSize - 1) % rowSize != rowSize - 1) {
                    //top left
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize - 1)) && (index + rowSize - 1) % rowSize != rowSize - 1) {
                    // bottom left
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize + 1)) && (index - rowSize + 1) % rowSize != 0) {
                    // top right
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize + 1)) && (index + rowSize + 1) % rowSize != 0) {
                    // bottom right
                    caughtSymbol = true
                }
            } else {
                if (caughtSymbol) {
                    numbers.add(currentNumber.toInt())
                }
                caughtSymbol = false
                currentNumber = ""
            }
        }

        return numbers.sum()
    }
}