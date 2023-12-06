package dec06

import org.junit.Assert
import org.junit.Test

class Dec06Test {
    @Test
    fun part1_test() {
        // Given
        val string = """Time:      7  15   30
Distance:  9  40  200""".trimIndent()
        val expected = 288L

        // When
        val actual = part1(string)

        // Then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun part2_test() {
        // Given
        val string = """Time:      7  15   30
Distance:  9  40  200""".trimIndent()
        val expected = 71503L

        // When
        val actual = part2(string)

        // Then
        Assert.assertEquals(expected, actual)
    }
}