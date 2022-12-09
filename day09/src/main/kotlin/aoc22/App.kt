package aoc22

import kotlinx.coroutines.coroutineScope

data class State(
    val planck: Int,
    val knots: MutableList<Pair<Long, Long>>
) {
    fun simulate(dir: Char, steps: Int): Iterable<Pair<Long, Long>> {
        val visited = mutableSetOf(knots.last())
        repeat(steps) {
            knots[0] = when(dir) {
                'L' -> knots.first().copy(knots.first().first.dec())
                'R' -> knots.first().copy(knots.first().first.inc())
                'U' -> knots.first().copy(second = knots.first().second.inc())
                'D' -> knots.first().copy(second = knots.first().second.dec())
                else -> throw IllegalArgumentException("$dir")
            }
            repeat(knots.size - 1) { i ->
                val dx = knots[i].first - knots[i + 1].first
                val dy = knots[i].second - knots[i + 1].second
                knots[i + 1] = when {
                    dx == 0L && dy > planck -> knots[i + 1].copy(second = knots[i + 1].second.inc())
                    dx == 0L && dy < -planck -> knots[i + 1].copy(second = knots[i + 1].second.dec())
                    dx > planck && dy == 0L -> knots[i + 1].copy(knots[i + 1].first.inc())
                    dx < -planck && dy == 0L -> knots[i + 1].copy(knots[i + 1].first.dec())
                    dx >= planck && dy > planck || dx > planck && dy >= planck -> knots[i + 1].copy(knots[i + 1].first.inc(), knots[i + 1].second.inc())
                    dx >= planck && dy < -planck || dx > planck && dy <= -planck -> knots[i + 1].copy(knots[i + 1].first.inc(), knots[i + 1].second.dec())
                    dx <= -planck && dy > planck || dx < -planck && dy >= planck -> knots[i + 1].copy(knots[i + 1].first.dec(), knots[i + 1].second.inc())
                    dx <= -planck && dy < -planck || dx < -planck && dy <= -planck -> knots[i + 1].copy(knots[i + 1].first.dec(), knots[i + 1].second.dec())
                    else -> knots[i + 1]
                }
            }
            visited += knots.last()
//            println(this)
        }
        return visited
    }
}

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filter { it.isNotBlank() }
        .map {it.split(' ')}
        .map { it.first().first() to it.last().toInt() }

    val visited = mutableSetOf<Pair<Long, Long>>()
    var state = State(1, MutableList(2) { 0L to 0L })
//    println(state)
    input.forEach {
//        println(it)
        visited += state.simulate(it.first, it.second)
    }
    println(visited.size)

    visited.clear()
    state.knots.apply {
        clear()
        repeat(10) {
            add(0L to 0L)
        }
    }
    input.forEach {
        visited += state.simulate(it.first, it.second)
    }
    println(visited.size)
}