package aoc

import kotlin.math.abs

class Solution(private val resName: String = "input", var take: Int = 1024) {
    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText()
        .lines()
        .filter { it.isNotEmpty() }
        .map {
            with(it.split(',')) { first().toInt() to last().toInt() }
        }
    val w = input.maxOf { it.first }
    val h = input.maxOf { it.second }

    val Pair<Int, Int>.neighbours get() = sequenceOf(
        first + 1 to second,
        first - 1 to second,
        first to second + 1,
        first to second - 1,
    ).filter { it.first in 0..w && it.second in 0..h } - input.take(take)

    fun aStar(
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        h: (Pair<Int, Int>) -> Int
    ): List<Pair<Int, Int>>? {
        val open = mutableSetOf(start)
        val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        val gScore = mutableMapOf(start to 0)
        val fScore = mutableMapOf(start to h(start))
        while (open.isNotEmpty()) {
            val current = open.minBy { fScore[it]!! }
            if (current == end) {
                val path = mutableListOf(current)
                var node = current
                while (cameFrom.containsKey(node)) {
                    node = cameFrom[node]!!
                    path.add(0, node)
                }
//                debug(w, this.h, input.take(take), path)
                return path
            }
            open -= current
            current.neighbours.forEach { neighbour ->
                val tentativeGScore = gScore[current]!! + 1
                if (tentativeGScore < gScore.getOrDefault(neighbour, Int.MAX_VALUE)) {
                    cameFrom[neighbour] = current
                    gScore[neighbour] = tentativeGScore
                    fScore[neighbour] = tentativeGScore + h(neighbour)
                    open += neighbour
                }
            }
        }
        return null
    }

    val part1 by lazy {
//        debug(input.take(take))
        aStar(
            0 to 0,
            w to h
        ) {
            abs(it.first - w) + abs(it.second - h)
        }/*?.also { debug(input.take(take), it) }*/?.size?.dec()
    }

    val part2 by lazy {
        val originalTake = take
        val result = input.indices.first {
//            println(it)
            take = it
            aStar(
                0 to 0,
                w to h
            ) {
                abs(it.first - w) + abs(it.second - h)
            } == null
        }
        take = originalTake
        input[result - 1]
    }

    companion object {
        fun debug(w: Int, h: Int, bytes: Iterable<Pair<Int, Int>>, path: Iterable<Pair<Int, Int>> = emptyList()) {
            for (y in 0..w) {
                for (x in 0..h) {
                    print(
                        when {
                            (x to y) in path -> 'O'
                            (x to y) in bytes -> '#'
                            else -> '.'
                        }
                    )
                }
                println()
            }
        }
    }
}
