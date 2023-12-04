package dec01

import java.io.File
import java.lang.Exception

fun String.asIntList(): List<Int> {
    return this.mapNotNull { it.digitToIntOrNull() }
}

fun part1(strings: List<String>): Int {
    return strings.sumOf {
        val ints = it.asIntList()
        val firstLast = "${ints.first()}${ints.last()}"
        firstLast.toInt()
    }
}

fun String.startsWithDigit(): Int? {
    return when {
        startsWith("one") -> 1
        startsWith("two") -> 2
        startsWith("three") -> 3
        startsWith("four") -> 4
        startsWith("five") -> 5
        startsWith("six") -> 6
        startsWith("seven") -> 7
        startsWith("eight") -> 8
        startsWith("nine") -> 9
        else -> null
    }
}

fun String.asIntListWithWords(): List<Int> {
    return this.indices.mapNotNull { index ->
        if (this[index].isDigit()) {
            this[index].digitToIntOrNull()
        } else {
            this.substring(index).startsWithDigit()
        }
    }
}

fun part2(strings: List<String>): Int {
    return strings.sumOf {
        val ints = it.asIntListWithWords()
        val firstLast = "${ints.first()}${ints.last()}"
        firstLast.toInt()
    }
}

fun main() {
    val strings = File("src/main/kotlin/dec01/input.txt").readLines()

    println("------------ PART 1 ------------")
    val answer1 = part1(strings)
    println("result: $answer1")

    println("------------ PART 2 ------------")
    val answer2 = part2(strings)
    println("result: $answer2")
}