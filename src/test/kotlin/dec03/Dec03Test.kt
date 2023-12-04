package dec03

import org.junit.Assert
import org.junit.Test

class Dec03Test {
    @Test
    fun part1_test() {
        // Given
        val string = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..""".trimIndent()
        val expected = 4361

        // When
        val actual = part1(string)

        // Then
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun part2_test() {
        // Given
        val string = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..""".trimIndent()
        val expected = 467835

        // When
        val actual = part2(string)

        // Then
        Assert.assertEquals(expected, actual)
    }
}