package aoc22

import kotlin.math.abs

fun reconstructPath(
    cameFrom: Map<Pair<Int, Int>, Pair<Int, Int>>,
    current: Pair<Int, Int>
): List<Pair<Int, Int>> {
    var curr = current
    val totalPath = mutableListOf(curr)
    while (curr in cameFrom.keys) {
        curr = cameFrom[curr]!!
        totalPath.add(curr)
    }
    return totalPath.reversed()
}

fun aStar(
    input: List<List<Char>>,
    start: Pair<Int, Int>,
    goal: Pair<Int, Int>,
    h: (Pair<Int, Int>) -> Int
): List<Pair<Int, Int>>? {
    val openSet = mutableSetOf(start)
    val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    val gScore = mutableMapOf(start to 0)
    val fScore = mutableMapOf(start to h(start))
    while (openSet.isNotEmpty()) {
        val current = openSet.minByOrNull { fScore[it] ?: Int.MAX_VALUE }!!
        if (current == goal) return reconstructPath(cameFrom, current)
        openSet -= current

        buildList {
            try {
                if (input[current.first + 1][current.second] <= input[current.first][current.second] + 1) {
                    add(current.first + 1 to current.second)
                }
            } catch (_: IndexOutOfBoundsException) {
            }
            try {
                if (input[current.first - 1][current.second] <= input[current.first][current.second] + 1) {
                    add(current.first - 1 to current.second)
                }
            } catch (_: IndexOutOfBoundsException) {
            }
            try {
                if (input[current.first][current.second + 1] <= input[current.first][current.second] + 1) {
                    add(current.first to current.second + 1)
                }
            } catch (_: IndexOutOfBoundsException) {
            }
            try {
                if (input[current.first][current.second - 1] <= input[current.first][current.second] + 1) {
                    add(current.first to current.second - 1)
                }
            } catch (_: IndexOutOfBoundsException) {
            }
        }.forEach { neighbor ->
            val tentative_gScore = (gScore[current] ?: (Int.MAX_VALUE - 1)) + 1
            if (tentative_gScore < (gScore[neighbor] ?: Int.MAX_VALUE)) {
                cameFrom[neighbor] = current
                gScore[neighbor] = tentative_gScore
                fScore[neighbor] = tentative_gScore + h(neighbor)
                openSet += neighbor
            }
        }
    }
    return null
}

fun main() {
    lateinit var start: Pair<Int, Int>
    lateinit var end: Pair<Int, Int>
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filter { it.isNotBlank() }
        .map { it.toList().toMutableList() }
        .also { map ->
            map.forEachIndexed { i, chars ->
                chars.forEachIndexed { j, c ->
                    when (c) {
                        'S' -> {
                            start = i to j
                            map[i][j] = 'a'
                        }

                        'E' -> {
                            end = i to j
                            map[i][j] = 'z'
                        }
                    }
                }
            }
        }
        .map { it.toList() }

//    input.forEach { println(it) }
//    println(start)
//    println(end)

    fun h(n: Pair<Int, Int>) = abs(n.first - end.first) + abs(n.second - end.second)

    println(
        aStar(input, start, end, ::h)!!.size - 1
    )
    println(
        input.flatMapIndexed{ i, row ->
            row.mapIndexedNotNull { j, c ->
                if (c == 'a') {
                    aStar(input, i to j, end, ::h)?.size?.dec()
                } else {
                    null
                }
            }
        }.min()
    )
}