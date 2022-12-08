package aoc22

import kotlinx.coroutines.coroutineScope

fun <T> Iterable<Iterable<T>>.print() {
    forEach { println(it) }
}

fun <T> List<List<T>>.row(i: Int) = get(i)
fun <T> List<List<T>>.col(i: Int) = map { it[i] }
fun <T: Comparable<T>> visible(x: T, trees: Collection<T>) = trees.indexOfFirst { it >= x }.let {
    if (it == -1) {
        trees.size - 1
    } else {
        it
    }
} + 1

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filter { it.isNotBlank() }
        .map { line ->
            line.map {
                it.digitToInt().toByte() }
        }
//    input.print()

//    println()
//    println(input.row(0))
//    println(input.col(0))

    val visibility = input.mapIndexed {i, row ->
        row.mapIndexed { j, x ->
            when {
                i == 0 || j == 0 || i == input.size - 1 || j == row.size - 1 -> true
                else -> row.take(j).all { it < x } || row.drop(j + 1).all { it < x } || input.col(j).take(i).all { it < x } || input.col(j).drop(i + 1).all { it < x }
            }
        }
    }
//    visibility.print()

    println(visibility.sumOf { it.count { it } })

    val scenic = input.mapIndexed {i, row ->
        row.mapIndexed { j, x ->
            when {
                i == 0 || j == 0 || i == input.size - 1 || j == row.size - 1 -> 0
                else -> visible(x, row.take(j).reversed()) * visible(x, row.drop(j + 1)) * visible(x, input.col(j).take(i).reversed()) * visible(x, input.col(j).drop(i + 1))
            }
        }
    }
//    scenic.print()

    println(scenic.maxOf { it.max() })
}