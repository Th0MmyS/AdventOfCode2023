package tasks.day02

import AbstractTask

const val RED_LIMIT = 12
const val GREEN_LIMIT = 13
const val BLUE_LIMIT = 14

class Task03 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        val games = parseGames(inputData)

        val validGameIds = mutableListOf<Int>()

        games.forEach { game ->
            if (game.hands.all { hand ->
                    hand.dice.all { dice ->
                        dice.color == Color.RED && dice.number <= RED_LIMIT ||
                                dice.color == Color.GREEN && dice.number <= GREEN_LIMIT ||
                                dice.color == Color.BLUE && dice.number <= BLUE_LIMIT
                    }
                }
            ) {
                validGameIds.add(game.id)
            }
        }

        return validGameIds.sum()
    }
}

fun parseGames(inputData: List<String>): MutableList<Game> {
    val games = mutableListOf<Game>()
    inputData.forEach {
        val split1 = it.split(":")
        val gameId = split1.first().split(" ").last().toInt()
        val game = Game(gameId, mutableListOf())

        val pulls = split1.last().split(";")
        pulls.forEach { a ->
            val hand = Hand(mutableListOf())
            val dice = a.split(",")
            dice.forEach { s ->
                val trimmed = s.trim().split(" ")
                val die = Dice(
                    trimmed.first().toInt(),
                    Color.valueOf(trimmed.last().uppercase())
                )
                hand.dice.add(die)
            }
            game.hands.add(hand)
        }
        games.add(game)
    }

    return games
}

data class Game constructor(
    val id: Int,
    val hands: MutableList<Hand>
)

data class Hand constructor(
    val dice: MutableList<Dice>
)

data class Dice constructor(
    val number: Int,
    val color: Color
)

enum class Color {
    RED, BLUE, GREEN
}