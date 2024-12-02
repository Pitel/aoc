package aoc

import kotlin.collections.windowed
import kotlin.math.abs
import kotlin.sequences.map

val Iterable<Int>.safe get() = (all { it > 0 } || all { it < 0 }) && all { abs(it) in 1..3 }
val Iterable<Int>.diffs get() = windowed(2) { it.first() - it.last() }

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }
        .map {
            it.split(' ').map { it.toInt() }
        }

    println(
        input
            .map { it.diffs }
            .map { it.safe }
            .count { it }
    )

    println(
        input
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
    )
}
