package aoc

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlin.math.min

class Solution(resName: String = "input") {
    data class Machine(
        val a: Pair<Long, Long>,
        val b: Pair<Long, Long>,
        val prize: Pair<Long, Long>,
        val pos: Pair<Long, Long> = 0L to 0L,
        val price: Long = 0,
    ) {
        val minPrice by lazy {
            var minPrice = Long.MAX_VALUE
            val states = mutableSetOf(this)
            while (states.isNotEmpty()) {
                val s = states.first()
                states.remove(s)
                if (s.pos.first < s.prize.first && s.pos.second < s.prize.second) {
                    states += copy(pos = s.pos + s.a, price = s.price + 3)
                    states += copy(pos = s.pos + s.b, price = s.price + 1)
                } else if (s.pos == s.prize) {
                    minPrice = min(minPrice, s.price)
                }
            }
            minPrice
        }

        companion object {
            operator fun Pair<Long, Long>.plus(other: Pair<Long, Long>) =
                (first + other.first) to (second + other.second)
        }
    }

    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText()
        .lineSequence()
        .filter { it.isNotEmpty() }
        .chunked(3)
        .map {
            Machine(
                NUMBERS_REGEX.find(it[0])!!.let { it.groupValues[1].toLong() to it.groupValues[2].toLong() },
                NUMBERS_REGEX.find(it[1])!!.let { it.groupValues[1].toLong() to it.groupValues[2].toLong() },
                NUMBERS_REGEX.find(it[2])!!.let { it.groupValues[1].toLong() to it.groupValues[2].toLong() },
            )
        }

    suspend fun part1() = input.map {
        GlobalScope.async { it.minPrice }
    }.toList().awaitAll().filter { it != Long.MAX_VALUE }.sum()

    suspend fun part2() = input.map {
        it.copy(prize = (it.prize.first + 10000000000000) to (it.prize.second + 10000000000000))
    }.map { GlobalScope.async { it.minPrice } }.toList().awaitAll().filter { it != Long.MAX_VALUE }.sum()

    companion object {
        val NUMBERS_REGEX = """(\d+)\D+(\d+)""".toRegex()
    }
}
