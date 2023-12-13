package tasks.day10

import AbstractTask

class Task19 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val mapWidth = inputData.first().length
        val map = inputData.joinToString(separator = "").toMutableList()

        var currentIndex = map.indexOf('S')
        var previousIndex = -1
        var currentPipe = 'S'
        var steps = 0

        while (currentPipe != 'S' || steps == 0) {
            var temp = canGoUp(map, currentPipe, currentIndex - mapWidth, previousIndex)?.let {
                steps++
                currentPipe = it
                previousIndex = currentIndex
                currentIndex -= mapWidth
            }
            if (temp != null) continue

            temp = canGoDown(map, currentPipe, currentIndex + mapWidth, previousIndex)?.let {
                steps++
                currentPipe = it
                previousIndex = currentIndex
                currentIndex += mapWidth
            }
            if (temp != null) continue

            temp = canGoLeft(map, currentPipe, currentIndex - 1, previousIndex)?.let {
                steps++
                currentPipe = it
                previousIndex = currentIndex
                currentIndex -= 1
            }
            if (temp != null) continue

            canGoRight(map, currentPipe, currentIndex + 1, previousIndex)?.let {
                steps++
                currentPipe = it
                previousIndex = currentIndex
                currentIndex += 1
            }
        }

        return steps / 2
    }

    private fun canGoUp(
        map: MutableList<Char>,
        currentPipe: Char,
        nextPipeIndex: Int,
        previousIndex: Int,
    ): Char? {
        if (nextPipeIndex == previousIndex) return null
        val nextSymbol = map.getOrNull(nextPipeIndex) ?: return null
        if (!setOf('L', 'J', 'S', '|').contains(currentPipe)) return null
        return setOf('7', 'F', 'S', '|').intersect(setOf(nextSymbol)).firstOrNull()
    }

    private fun canGoDown(
        map: MutableList<Char>,
        currentPipe: Char,
        nextPipeIndex: Int,
        previousIndex: Int,
    ): Char? {
        if (nextPipeIndex == previousIndex) return null
        val nextSymbol = map.getOrNull(nextPipeIndex) ?: return null
        if (!setOf('F', '7', 'S', '|').contains(currentPipe)) return null
        return setOf('J', 'L', 'S', '|').intersect(setOf(nextSymbol)).firstOrNull()
    }

    private fun canGoLeft(
        map: MutableList<Char>,
        currentPipe: Char,
        nextPipeIndex: Int,
        previousIndex: Int,
    ): Char? {
        if (nextPipeIndex == previousIndex) return null
        val nextSymbol = map.getOrNull(nextPipeIndex) ?: return null
        if (!setOf('7', 'J', 'S', '-').contains(currentPipe)) return null
        return setOf('L', 'F', 'S', '-').intersect(setOf(nextSymbol)).firstOrNull()
    }

    private fun canGoRight(
        map: MutableList<Char>,
        currentPipe: Char,
        nextPipeIndex: Int,
        previousIndex: Int,
    ): Char? {
        if (nextPipeIndex == previousIndex) return null
        val nextSymbol = map.getOrNull(nextPipeIndex) ?: return null
        if (!setOf('L', 'F', 'S', '-').contains(currentPipe)) return null
        return setOf('7', 'J', 'S', '-').intersect(setOf(nextSymbol)).firstOrNull()
    }
}
