package aoc

import kotlin.math.max

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filterNot { it.isBlank() }
        .map { it.split(':', limit = 2).last().trim() }
        .map { game ->
            game.split(';')
                .map { cubes ->
                    cubes.split(',')
                        .map { it.trim() }
                        .map { it.split(' ') }
                }
                .map { cubes ->
                    var r = 0
                    var g = 0
                    var b = 0
                    cubes.forEach {
                        when (it.last()) {
                            "red" -> r = it.first().toInt()
                            "green" -> g = it.first().toInt()
                            "blue" -> b = it.first().toInt()
                        }
                    }
                    RGB(r, g, b)
                }
        }

    println(
        input.foldIndexed(0) { i, sum, game ->
            sum + if (game.all { it.possible }) {
                i.inc()
            } else {
                0
            }
        }
    )

    println(
        input.sumOf { game ->
            game.reduce { acc, cube ->
                acc.copy(
                    r = max(acc.r, cube.r),
                    g = max(acc.g, cube.g),
                    b = max(acc.b, cube.b),
                )
            }.power
        }
    )
}
