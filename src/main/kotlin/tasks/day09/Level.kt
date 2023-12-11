package tasks.day09

data class Level constructor(
    val bricks: List<Long>
) {
    fun containsAllZeroes() = bricks.all { it == 0L }
}