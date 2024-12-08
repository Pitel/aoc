package aoc

import kotlin.math.abs
import kotlin.math.max

class Solution(resName: String = "input") {
    val input: List<String> = object {}.javaClass.getResource("/$resName.txt")!!
        .readText().lines().filter { it.isNotEmpty() }

    val antennas = buildMap {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c != '.') {
                    put(c, getOrDefault(c, emptyList()) + (x to y))
                }
            }
        }
    }

    fun antinodes(harmonics: Boolean) = buildSet {
        antennas.values.forEach {
            it.pairs.forEach {
                val a1 = it.first
                val a2 = it.second
                val dx = a1.first - a2.first
                val dy = a1.second - a2.second
                check(dy <= 0)
                val adx = abs(dx)
                val ady = abs(dy)
                if (harmonics) {
                    add(a1)
                    add(a2)
                }
                repeat(if (harmonics) max(input.size, input.first().length) else 1) { i ->
                    if (dx < 0) {
                        add((a1.first - adx * i.inc()) to (a1.second - ady * i.inc()))
                        add((a2.first + adx * i.inc()) to (a2.second + ady * i.inc()))
                    } else {
                        add((a1.first + adx * i.inc()) to (a1.second - ady * i.inc()))
                        add((a2.first - adx * i.inc()) to (a2.second + ady * i.inc()))
                    }
                }
            }
        }
    }.filter { it.first in input.first().indices && it.second in input.indices }

    val part1 = antinodes(false).size
    val part2 = antinodes(true).size

    companion object {
        val <T> Iterable<T>.pairs get() = sequence {
            this@pairs.forEachIndexed { i, pos ->
                this@pairs.drop(i + 1).forEach {
                    yield(pos to it)
                }
            }
        }
    }
}
