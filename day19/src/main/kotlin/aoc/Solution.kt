package aoc

class Solution(private val resName: String = "input") {
    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText()
        .lines()
        .filter { it.isNotEmpty() }

    val regex = "(${input.first().replace(", ", "|")})+".toRegex()

    val part1 by lazy {
        input.count { regex.matchEntire(it) != null }
    }
    val part2 by lazy { 0 }
}
