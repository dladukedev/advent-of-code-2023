package dec03

import java.io.File

data class Point(
    val x: Int,
    val y: Int,
) {
    val neighbors: List<Point> get() = listOf(
        Point(x-1, y+1),
        Point(x-1, y),
        Point(x-1, y-1),

        Point(x, y+1),
        Point(x, y-1),

        Point(x+1, y+1),
        Point(x+1, y),
        Point(x+1, y-1),
    )

}

data class Part(
    val startIndex: Point,
    val endIndex: Point,
) {
    fun containsPoint(point: Point): Boolean {
        if(point.y != startIndex.y) return false

        return (startIndex.x..endIndex.x).contains(point.x)
    }
}

data class Schematic(
    val data: List<List<Char>>
) {
    val width: Int = data.first().size
    val height: Int = data.size

    fun getPoint(x: Int, y: Int): Char {
        return data[y][x]
    }

    fun getRow(y: Int): List<Char> {
        return data[y]
    }

    fun partValue(part: Part): Int {
        return data[part.startIndex.y].subList(part.startIndex.x, part.endIndex.x + 1).joinToString("").toInt()
    }
}

fun isEnginePart(part: Part, schematic: Schematic): Boolean {
    val startNeighbors = listOf(
        schematic.getPoint(part.startIndex.x - 1, part.startIndex.y),
        schematic.getPoint(part.startIndex.x - 1, part.startIndex.y - 1),
        schematic.getPoint(part.startIndex.x - 1, part.startIndex.y + 1),
    )

    val endNeighbors = listOf(
        schematic.getPoint(part.endIndex.x + 1, part.endIndex.y),
        schematic.getPoint(part.endIndex.x + 1, part.endIndex.y - 1),
        schematic.getPoint(part.endIndex.x + 1, part.endIndex.y + 1),
    )

    val middleNeighborBelow = (part.startIndex.x..part.endIndex.x).map { x ->
        schematic.getPoint(x, part.startIndex.y - 1)
    }

    val middleNeighborAbove = (part.startIndex.x..part.endIndex.x).map { x ->
        schematic.getPoint(x, part.startIndex.y + 1)
    }

    val allPoints = startNeighbors + endNeighbors + middleNeighborAbove + middleNeighborBelow

    return allPoints.any { it != '.' }
}

fun parseSchematic(string: String): Schematic {
    val data = string.lines()
        .map { it.toList() }

    return Schematic(data)
}

fun padSchematic(schematic: Schematic): Schematic {
    val newData = listOf(List(schematic.width + 2) { '.' }) +
            schematic.data.map { listOf('.') + it + listOf('.') } +
            listOf(List(schematic.width + 2) { '.' })

    return Schematic(newData)
}

fun parseParts(schematic: Schematic): List<Part> {
    return schematic.data.indices.flatMap { y ->
        schematic.getRow(y).indices.map { x ->
            val point = schematic.getPoint(x, y)
            when {
                point == '.' -> null
                point.isDigit() && schematic.getPoint(x - 1, y).isDigit().not() -> Point(x, y)
                else -> null
            }
        }
    }.filterNotNull()
        .map { startPoint ->
            val row = schematic.getRow(startPoint.y)
            val point = row.subList(startPoint.x, row.size).takeWhile { it.isDigit() }
            val endPoint = Point(startPoint.x + point.size - 1, startPoint.y)
            Part(startPoint, endPoint)
        }
}


fun part1(string: String): Int {
    val schematic = padSchematic(parseSchematic(string))

    val parts = parseParts(schematic)

    return parts.filter { isEnginePart(it, schematic) }.sumOf { schematic.partValue(it) }
}

fun parseGears(schematic: Schematic): List<Point> {
    return schematic.data.indices.flatMap { y ->
        schematic.getRow(y).indices.map { x ->
            when (schematic.getPoint(x, y)) {
                '*' -> Point(x, y)
                else -> null
            }
        }
    }.filterNotNull()
}

fun getNeighborParts(point: Point, parts: List<Part>): List<Part> {
    val x = point.neighbors.flatMap {neighborPoint ->
        parts.filter { it.containsPoint(neighborPoint) }
    }.distinct()

//    println(x)
    return x
}

fun part2(string: String): Int {
    val schematic = padSchematic(parseSchematic(string))
    val parts = parseParts(schematic)

    return parseGears(schematic)
        .map { gear -> getNeighborParts(gear, parts) }
        .filter { it.size == 2 }
        .map { neighborParts -> neighborParts .map { schematic.partValue(it) }}
        .sumOf { it[0] * it[1] }
}

fun main() {
    val strings = File("src/main/kotlin/dec03/input.txt").readLines().joinToString("\n")

    println("------------ PART 1 ------------")
    val answer1 = part1(strings)
    println("result: $answer1")

    println("------------ PART 2 ------------")
    val answer2 = part2(strings)
    println("result: $answer2")
}