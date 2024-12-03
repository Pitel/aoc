package aoc

import kotlin.math.abs

object Solution {
    private val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }
        .map {
            it.split(' ').map { it.toInt() }
        }

    private val Iterable<Int>.safe get() = (all { it > 0 } || all { it < 0 }) && all { abs(it) in 1..3 }
    private val Iterable<Int>.diffs get() = windowed(2) { it.first() - it.last() }

    val part1 = input
        .map { it.diffs }
        .map { it.safe }
        .count { it }

    val part2 = input
        .map { line ->
            buildList {
                add(line)
                line.forEachIndexed { i, _ ->
                    add(line.toMutableList().apply { removeAt(i) })
                }
            }
        }
        .map { it.map { it.diffs } }
        .map { it.any { it.safe } }
        .count { it }
}
