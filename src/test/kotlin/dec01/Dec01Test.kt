package dec01

import org.junit.Assert
import org.junit.Test

class Dec01Test {
    @Test
    fun part1_test() {
        // Given
        val strings = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        )
        val expected = 142

        // When
        val actual = part1(strings)

        // Then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun part2_test() {
        // Given
        val strings = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        )
        val expected = 281

        // When
        val actual = part2(strings)

        // Then
        Assert.assertEquals(expected, actual)
    }
}