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
        input.map { rucksack ->
            async {
                val compartments = rucksack.chunked(rucksack.length / 2).map { it.toSet() }
                val intersect = compartments.first().intersect(compartments.last()).first()
                intersect - (if (intersect >= 'a') 'a' else ('A' - 26)) + 1
            }
        }.awaitAll().sum()
    )

    println(
        input.chunked(3).map { rucksacks ->
            async {
                val badge = rucksacks.map { it.toSet() }.reduce { acc, rucksack ->
                    acc.intersect(rucksack)
                }.first()
                badge - (if (badge >= 'a') 'a' else ('A' - 26)) + 1
            }
        }.awaitAll().sum()
    )
}