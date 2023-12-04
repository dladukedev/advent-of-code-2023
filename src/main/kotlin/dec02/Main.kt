package dec02

import java.io.File

data class Game(
    val id: Int,
    val pulls: List<Pull>,
) {
    fun maxPull(): Pull {
        val red = pulls.maxOf { it.red }
        val green = pulls.maxOf { it.green }
        val blue = pulls.maxOf { it.blue }

        return Pull(red, green, blue)
    }
}

data class Pull(
    val red: Int,
    val green: Int,
    val blue: Int,
)

fun String.asGame(): Game {
    val (game, rawPulls) = this.split(": ")
    val gameId = game.substringAfter(" ").toInt()

    val pulls = rawPulls.split("; ").map { rawPull ->
        val cubes = rawPull.split(", ")
        val red = cubes.firstOrNull { it.endsWith("red")}?.takeWhile { it != ' ' }?.toInt() ?: 0
        val green = cubes.firstOrNull { it.endsWith("green")}?.takeWhile { it != ' ' }?.toInt() ?: 0
        val blue = cubes.firstOrNull { it.endsWith("blue")}?.takeWhile { it != ' ' }?.toInt() ?: 0

        Pull(red, green, blue)
    }

    return Game(gameId, pulls)
}

fun part1(strings: List<String>): Int {
    val games = strings.map { it.asGame() }

    return games.filter {
        val maxPull = it.maxPull()
        maxPull.red <= 12 && maxPull.green <= 13 && maxPull.blue <= 14
    }.sumOf { it.id }
}

fun part2(strings: List<String>): Int {
    val games = strings.map { it.asGame() }

    return games.sumOf {
        val maxPull = it.maxPull()
        maxPull.red * maxPull.green * maxPull.blue
    }
}


fun main() {
    val strings = File("src/main/kotlin/dec02/input.txt").readLines()

    println("------------ PART 1 ------------")
    val answer1 = part1(strings)
    println("result: $answer1")

    println("------------ PART 2 ------------")
    val answer2 = part2(strings)
    println("result: $answer2")
}