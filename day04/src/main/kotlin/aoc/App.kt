package aoc

import kotlin.math.pow

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filterNot { it.isBlank() }
        .map { line ->
            line.split(':').last().split('|').map {
                it.split(' ').filterNot { it.isBlank() }.map { it.toInt() }.toSet()
            }
        }
//    println(input)

    println(
        input.sumOf {
            2f.pow(it.reduce { acc, ints -> acc.intersect(ints) }.size.dec()).toInt()
        }
    )

    val copies = MutableList(input.size) { 1 }
    input.forEachIndexed { i, sets ->
        val wins = sets.reduce { acc, ints -> acc.intersect(ints) }.size
        repeat(wins) {
            copies[i.inc() + it] += copies[i]
        }
    }
    println(copies.sum())
}
