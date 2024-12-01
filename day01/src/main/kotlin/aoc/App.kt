package aoc

import kotlin.math.abs

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }

    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    input.forEach {
        val l = it.split("   ").map { it.toInt() }
        left += l.first()
        right += l.last()
    }

    println(
        left.sorted().zip(right.sorted()) { l, r -> abs(l - r) }.sum()
    )

    println(
        left.sumOf { l -> l * right.count { r -> l == r } }
    )
}
