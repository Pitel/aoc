package aoc22

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

fun score1(elf: Char, me: Char) = when (elf) {
    //kamen
    'A' -> when (me) {
        'X' -> 1 + 3 //kamen
        'Y' -> 2 + 6 //papir
        'Z' -> 3 + 0 //nuzky
        else -> throw IllegalArgumentException("$me")
    }

    //papir
    'B' -> when (me) {
        'X' -> 1 + 0 //kamen
        'Y' -> 2 + 3 //papir
        'Z' -> 3 + 6 //nuzky
        else -> throw IllegalArgumentException("$me")
    }

    //nuzky
    'C' -> when (me) {
        'X' -> 1 + 6 //kamen
        'Y' -> 2 + 0 //papir
        'Z' -> 3 + 3 //nuzky
        else -> throw IllegalArgumentException("$me")
    }

    else -> throw IllegalArgumentException("$elf")
}

fun score2(elf: Char, out: Char) = when (elf) {
    //kamen
    'A' -> when (out) {
        'X' -> 3 + 0 //lose
        'Y' -> 1 + 3 //draw
        'Z' -> 2 + 6 //win
        else -> throw IllegalArgumentException("$out")
    }

    //papir
    'B' -> when (out) {
        'X' -> 1 + 0 //lose
        'Y' -> 2 + 3 //draw
        'Z' -> 3 + 6 //win
        else -> throw IllegalArgumentException("$out")
    }

    //nuzky
    'C' -> when (out) {
        'X' -> 2 + 0 //lose
        'Y' -> 3 + 3 //draw
        'Z' -> 1 + 6 //win
        else -> throw IllegalArgumentException("$out")
    }

    else -> throw IllegalArgumentException("$elf")
}

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filter { it.isNotBlank() }

    println(input.map { async { score1(it.first(), it.last()) } }.awaitAll().sum())
    println(input.map { async { score2(it.first(), it.last()) } }.awaitAll().sum())
}