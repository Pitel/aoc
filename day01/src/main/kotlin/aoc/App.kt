package aoc

import kotlin.math.abs

fun main() {
    val (left, right) = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }
        .map {
            val l = it.split("   ")
                .map { it.toInt() }
            l.first() to l.last()
        }.unzip()

    println(
        left.sorted().zip(right.sorted()) { l, r -> abs(l - r) }.sum()
    )

    println(
        left.sumOf { l -> l * right.count { r -> l == r } }
    )
}
