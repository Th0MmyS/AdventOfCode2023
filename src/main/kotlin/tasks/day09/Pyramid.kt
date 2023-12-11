package tasks.day09

data class Pyramid constructor(
    val levels: MutableList<Level>
) {
    fun calculateNextNumberInFirstLevel() = levels.reversed().drop(1)
        .fold(levels.last().bricks.last()) { prev, next -> next.bricks.last() + prev }

    fun calculatePreviousNumberInFirstLevel() = levels.reversed().drop(1)
        .fold(levels.last().bricks.first()) { prev, next -> next.bricks.first() - prev }

    fun generateLevels() {
        while (!levels.last().containsAllZeroes()) {
            val newLevel = mutableListOf<Long>()
            val lastLayerBricks = levels.last().bricks
            lastLayerBricks.forEachIndexed { index, brick ->
                if (index + 1 < lastLayerBricks.size) {
                    newLevel.add(lastLayerBricks[index + 1] - brick)
                }
            }

            levels.add(Level(newLevel))
        }
    }
}
