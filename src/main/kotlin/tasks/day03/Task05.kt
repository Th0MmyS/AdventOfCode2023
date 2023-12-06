package tasks.day03

import AbstractTask
import log

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

                if (((index - 1) % rowSize != 9) && hasSymbol(oneLine.getOrNull(index - 1))) {
                    println("left failed")
                    //left
                    caughtSymbol = true
                }

                if (((index + 1) % rowSize != 0) && hasSymbol(oneLine.getOrNull(index + 1))) {
                    println("right failed")
                    // right
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize))) {
                    println("above failed")
                    // above
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize))) {
                    println("below failed")
                    // below
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize - 1)) && (index - rowSize - 1) % rowSize != 9) {
                    println("top left failed")
                    //top left
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize - 1)) && (index + rowSize - 1) % rowSize != 9) {
                    println("bottom left failed")
                    // bottom left
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index - rowSize + 1)) && (index - rowSize + 1) % rowSize != 0) {
                    println("top right failed")
                    // top right
                    caughtSymbol = true
                }

                if (hasSymbol(oneLine.getOrNull(index + rowSize + 1)) && (index + rowSize + 1) % rowSize != 0) {
                    println("bottom right failed")
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

        numbers.log()

        // 528799
//        553079

        return numbers.sum()
    }
}

//
//oneLine.forEachIndexed { index, letter ->
//    println("-------")
//    if (!letter.isDigit()) {
//        println("have some number $currentNumber")
//        currentNumber = if (caughtSymbol) {
//            println("adding number $currentNumber")
//            numbers.add(currentNumber.toInt())
//            currentNumber.log()
//            ""
//        } else {
//            ""
//        }
//        caughtSymbol = false
//    } else {
//        if (letter.isDigit()) {
//            println("handling $letter")
//            println("previous letter " + oneLine.getOrNull(index - 1))
//            println("last known letter " + currentNumber.lastOrNull())
//            println("currentNumber is $currentNumber")
//            println("catchedSymbol is $caughtSymbol")
//            println("temporally add $letter")
//            currentNumber += letter.digitToInt()
//
//        } else {
//            if (((index - 1) % rowSize != 9) && hasSymbol(oneLine.getOrNull(index - 1))) {
//                println("left failed")
//                //left
//                caughtSymbol = true
//            }
//
//            if (((index + 1) % rowSize != 0) && hasSymbol(oneLine.getOrNull(index + 1))) {
//                println("right failed")
//                // right
//                caughtSymbol = true
//            }
//
//            if (hasSymbol(oneLine.getOrNull(index - rowSize))) {
//                println("above failed")
//                // above
//                caughtSymbol = true
//            }
//
//            if (hasSymbol(oneLine.getOrNull(index + rowSize))) {
//                println("below failed")
//                // below
//                caughtSymbol = true
//            }
//
//            if (hasSymbol(oneLine.getOrNull(index - rowSize - 1)) && (index - rowSize - 1) % rowSize != 9) {
//                println("top left failed")
//                //top left
//                caughtSymbol = true
//            }
//
//            if (hasSymbol(oneLine.getOrNull(index + rowSize - 1)) && (index + rowSize - 1) % rowSize != 9) {
//                println("bottom left failed")
//                // bottom left
//                caughtSymbol = true
//            }
//
//            if (hasSymbol(oneLine.getOrNull(index - rowSize + 1)) && (index - rowSize + 1) % rowSize != 0) {
//                println("top right failed")
//                // top right
//                caughtSymbol = true
//            }
//
//            if (hasSymbol(oneLine.getOrNull(index + rowSize + 1)) && (index + rowSize + 1) % rowSize != 0) {
//                println("bottom right failed")
//                // bottom right
//                caughtSymbol = true
//            }
//        }
//    }
//}