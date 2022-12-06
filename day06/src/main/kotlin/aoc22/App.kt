package aoc22

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filter { it.isNotBlank() }


    println(
        input.map { line ->
            val size = 4
            async {
                line.windowed(size).indexOfFirst {
//                    println(it)
                    it.toSet().size == size
                } + size
            }
        }.awaitAll().last()
    )

    println(
        input.map { line ->
            val size = 14
            async {
                line.windowed(size).indexOfFirst {
//                    println(it)
                    it.toSet().size == size
                } + size
            }
        }.awaitAll().last()
    )
}