package dec04

import java.io.File
import kotlin.math.pow

data class Game(
    val id: Int,
    val winningNumbers: List<Int>,
    val playerNumbers: List<Int>,
) {
    val score: Int get() {
        val playerWinningNumbers = winningNumbers.intersect(playerNumbers)

        return 2.0.pow(playerWinningNumbers.size - 1).toInt()
    }

    val wins: Int get() {
        return winningNumbers.intersect(playerNumbers).size
    }

    constructor(string: String): this(
        id = string.split(":").first().split(" ").last().toInt(),
        winningNumbers = string.split(": ").last().split(" |").first().split(" ").filter { it.isNotBlank() }.map { it.toInt() },
        playerNumbers = string.split(": ").last().split("| ").last().split(" ").filter { it.isNotBlank() }.map { it.toInt() },
    )
}

fun part1(string: String): Int {
    return string.lines()
        .map { Game(it) }
        .sumOf { it.score }
}

fun part2(string: String): Long {
    val x =  string.lines()
        .map { Game(it) }
//        .forEach { println("Game ${it.id} - ${it.wins}") }

    val init = x.associate { it.id to 1L }.toMutableMap()

    val result = x.fold(init) { acc, game ->
        val cardCount = acc.getValue(game.id)

        (game.id + 1..(game.id + game.wins)).forEach {id ->
            acc[id] = acc.getValue(id) + cardCount
        }

        acc
    }.values.sum()

    return result
}

fun main() {
    val strings = File("src/main/kotlin/dec04/input.txt").readLines().joinToString("\n")

    println("------------ PART 1 ------------")
    val answer1 = part1(strings)
    println("result: $answer1")

    println("------------ PART 2 ------------")
    val answer2 = part2(strings)
    println("result: $answer2")
}