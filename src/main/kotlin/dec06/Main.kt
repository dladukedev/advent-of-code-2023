package dec06

import java.io.File

data class Race(
    val duration: Long,
    val distance: Long,
) {
    val winningCombinations: Long get() {
        return (0..duration).count { holdDuration ->
            val speed = holdDuration
            val remainingDuration = duration - holdDuration

            val travelled = speed * remainingDuration

            travelled > distance
        }.toLong()
    }
}

fun parseRaces(input: String): List<Race> {
    val (durations, distances) = input.lines()
        .map { line -> line.split(" ").drop(1).filterNot { it.isEmpty() }.map { it.toLong() } }

    return durations.mapIndexed { index, duration -> Race(duration, distances[index]) }
}

fun parseRace(input: String): Race {
    val (duration, distance) = input.lines()
        .map { line -> line.split(" ").drop(1).filterNot { it.isEmpty() }.joinToString("").toLong() }

    return Race(duration, distance)
}

fun part1(input: String): Long {
    return parseRaces(input).fold(1L) { acc, race -> acc * race.winningCombinations }
}

fun part2(input: String): Long {
    return parseRace(input).winningCombinations
}

fun main() {
    val input = File("src/main/kotlin/dec06/input.txt").readLines().joinToString("\n")

    println("------------ PART 1 ------------")
    val answer1 = part1(input)
    println("result: $answer1")

    println("------------ PART 2 ------------")
    val answer2 = part2(input)
    println("result: $answer2")
}
