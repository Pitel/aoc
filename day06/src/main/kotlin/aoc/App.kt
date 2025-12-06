package aoc

import kotlin.text.foldIndexed

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filterNot { it.isEmpty() }

    val ops = input.last().replace(" ", "")
    println(ops)
    val numbers = input.dropLast(1).map { line ->
        line.split(' ').filterNot { it.isEmpty() }.map { it.toULong() }
    }
    println(numbers)

    println(
        ops.foldIndexed(0uL) { i, sum, op ->
            sum + when (op) {
                '*' -> numbers.fold(1uL) { acc, line -> acc * line[i] }
                '+' -> numbers.sumOf { it[i] }
                else -> 0uL
            }
        }
    )

//    println(input.map { it.jolts(2) }.sum())
//    println(input.map { it.jolts(12) }.sum())
}
