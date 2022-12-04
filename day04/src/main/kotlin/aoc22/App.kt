package aoc22

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filter { it.isNotBlank() }
        .map { line ->
            line.split(',')
                .map { it.split('-') }
                .map { it.first().toInt()..it.last().toInt() }
                .map { it.toSet() }
        }

    println(
        input.map { section ->
            async {
                (section.first() - section.last()).isEmpty() || (section.last() - section.first()).isEmpty()
            }
        }.awaitAll().count { it }
    )

    println(
        input.map { section ->
            async {
                section.first().intersect(section.last()).isNotEmpty()
            }
        }.awaitAll().count { it }
    )
}