package tasks.day03

import AbstractTask
import log

private val bannedSymbols = listOf('#', '*', '+', '$', '/', '@', '=', '-', '%')

class Task05 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    private fun hashSymbol(char: Char?): Boolean {
        println("processing $char")
        char ?: return false
        return bannedSymbols.contains(char)
    }

    override fun calculate(): Int {
        val rowSize = inputData.first().length
        val oneLine = inputData.joinToString("")

        val numbers = mutableListOf<Int>()

        var currentNumber = ""

        oneLine.forEachIndexed { index, letter ->
            println("-------")
            if (letter == '.' && currentNumber.isNotBlank()) {
                println("adding number $currentNumber")
                numbers.add(currentNumber.toInt())
                currentNumber.log()
                currentNumber = ""
            } else if (!bannedSymbols.contains(letter) && letter != '.') {
                if (letter.isDigit()) {
                    println("handling $letter")
                    println("previous letter " + oneLine.getOrNull(index - 1))
                    println("last known letter " + currentNumber.lastOrNull())
                    println("currentNumber is $currentNumber")
                    if (oneLine.getOrNull(index - 1) == currentNumber.lastOrNull() || oneLine.getOrNull(index - 1) == '.') {
                        println("temporally add $letter")
                        currentNumber += letter.digitToInt()
                    }
                }

                if (((index - 1) % rowSize != 9) && hashSymbol(oneLine.getOrNull(index - 1))) {
                    println("left failed")
                    //left
                    currentNumber = ""
                }

                if (((index + 1) % rowSize != 0) && hashSymbol(oneLine.getOrNull(index + 1))) {
                    println("right failed")
                    // right
                    currentNumber = ""
                }

                if (hashSymbol(oneLine.getOrNull(index - rowSize))) {
                    println("above failed")
                    // above
                    currentNumber = ""
                }

                if (hashSymbol(oneLine.getOrNull(index + rowSize))) {
                    println("below failed")
                    // below
                    currentNumber = ""
                }

                if (hashSymbol(oneLine.getOrNull(index - rowSize - 1)) && (index - rowSize - 1) % rowSize != 9) {
                    println("top left failed")
                    //top left
                    currentNumber = ""
                }

                if (hashSymbol(oneLine.getOrNull(index + rowSize - 1)) && (index + rowSize - 1) % rowSize != 9) {
                    println("bottom left failed")
                    // bottom left
                    currentNumber = ""
                }

                if (hashSymbol(oneLine.getOrNull(index - rowSize + 1)) && (index - rowSize + 1) % rowSize != 0) {
                    println("top right failed")
                    // top right
                    currentNumber = ""
                }

                if (hashSymbol(oneLine.getOrNull(index + rowSize + 1)) && (index + rowSize + 1) % rowSize != 0) {
                    println("bottom right failed")
                    // bottom right
                    currentNumber = ""
                }
            }
        }

        numbers.log()

        return numbers.sum()
    }

//    override fun calculate(): Int {
//        val rowSize = inputData.first().length
//        val oneLine = inputData.joinToString("")
//
//        val numbers = mutableListOf<Int>()
//
//        var currentNumber = ""
//
//        oneLine.forEachIndexed asd@{ index, letter ->
//            println("-------")
//            if (letter == '.' && currentNumber.isNotBlank()) {
//                println("adding number $currentNumber")
//                numbers.add(currentNumber.toInt())
//                currentNumber.log()
//                currentNumber = ""
//            } else if (!bannedSymbols.contains(letter) && letter != '.') {
//                if (letter.isDigit()) {
//                    println("handling $letter")
//                    println("previous letter " + oneLine.getOrNull(index - 1))
//                    println("last known letter " + currentNumber.lastOrNull())
//                    println("currentNumber is $currentNumber")
//                    if (oneLine.getOrNull(index - 1) == currentNumber.lastOrNull() || oneLine.getOrNull(index - 1) == '.') {
//                        println("temporally add $letter")
//                        currentNumber += letter.digitToInt()
//                    }
//                }
//
//                if (((index - 1) % rowSize != 9) && hashSymbol(oneLine.getOrNull(index - 1))) {
//                    println("left failed")
//                    //left
//                    currentNumber = ""
//                }
//
//                if (((index + 1) % rowSize != 0) && hashSymbol(oneLine.getOrNull(index + 1))) {
//                    println("right failed")
//                    // right
//                    currentNumber = ""
//                }
//
//                if (hashSymbol(oneLine.getOrNull(index - rowSize))) {
//                    println("above failed")
//                    // above
//                    currentNumber = ""
//                }
//
//                if (hashSymbol(oneLine.getOrNull(index + rowSize))) {
//                    println("below failed")
//                    // below
//                    currentNumber = ""
//                }
//
//                if (hashSymbol(oneLine.getOrNull(index - rowSize - 1)) && (index - rowSize - 1) % rowSize != 9) {
//                    println("bottom top failed")
//                    //top left
//                    currentNumber = ""
//                }
//
//                if (hashSymbol(oneLine.getOrNull(index + rowSize - 1)) && (index + rowSize - 1) % rowSize != 9) {
//                    println("bottom left failed")
//                    // bottom left
//                    currentNumber = ""
//                }
//
//                if (hashSymbol(oneLine.getOrNull(index - rowSize + 1)) && (index - rowSize + 1) % rowSize != 0) {
//                    println("top right failed")
//                    // top right
//                    currentNumber = ""
//                }
//
//                if (hashSymbol(oneLine.getOrNull(index + rowSize + 1)) && (index + rowSize + 1) % rowSize != 0) {
//                    println("bottom right failed")
//                    // bottom right
//                    currentNumber = ""
//                }
//            }
//        }
//
//        numbers.log()
//
//        return numbers.sum()
//    }
}