package aoc

object Solution {
    private val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()

    val MUL = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    val part1 = input.flatMap {
        MUL.findAll(it).map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }.sum()

    val part2 = 0
}
