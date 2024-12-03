package aoc

import kotlin.sequences.map
import kotlin.sequences.sum
import kotlin.text.split
import kotlin.text.substringBefore

object Solution {
    private val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()

    val MUL = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
    const val DO = "do()"
    const val DONT = "don't()"
    val String.mulSum get() = MUL.findAll(this).map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }.sum()

    val part1 = input.map { it.mulSum }.sum()

    fun part2(input: String) = input.split(DO).map { it.substringBefore(DONT) }.sumOf { it.mulSum }

    val part2 = input.map { part2(it) }.sum()
}
