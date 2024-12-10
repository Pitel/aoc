package aoc

import kotlinx.coroutines.awaitAll

suspend fun main() {
    println(Solution().part1.awaitAll().sum())
    println(Solution().part2.awaitAll().sum())
}
