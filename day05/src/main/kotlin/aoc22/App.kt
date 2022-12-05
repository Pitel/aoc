package aoc22

import kotlinx.coroutines.coroutineScope

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()

    val stacks_ = input
        .takeWhile { it.isNotBlank() }
        .map { line ->
            line.chunked(4).map { block ->
                block.filter { it.isLetterOrDigit() }
            }
        }
    println(stacks_)
    val stacks = List(stacks_.first().size) { i ->
        List(stacks_.size - 1) { j ->
            stacks_[j][i]
        }
    }
        .map { it.reversed().mapNotNull { it.firstOrNull() }.toMutableList() }.toMutableList()
    println(stacks)

    val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    input
        .dropWhile { it.isNotBlank() }
        .filter { it.isNotBlank() }
        .map { regex.matchEntire(it) }
        .map { it!!.groupValues.drop(1).map { it.toInt() } }
//        .also { println(it) }
        .forEach {
            println(it)
            println(stacks)
            //stacks[it[2] - 1] += stacks[it[1] - 1].takeLast(it[0]).reversed() // Part 1
            stacks[it[2] - 1] += stacks[it[1] - 1].takeLast(it[0]) // Part 2
            stacks[it[1] - 1] = stacks[it[1] - 1].dropLast(it[0]).toMutableList()
            println(stacks)
            println("============")
        }
    println(stacks.map { it.last() }.joinToString(""))
}