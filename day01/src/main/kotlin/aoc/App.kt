package aoc

import kotlin.math.abs

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }
        .map {
            val direction = Dial.Direction.valueOf("${it.first()}")
            val steps = it.drop(1).toInt()
            direction to steps
        }

    var dial = Dial(99, 50)
    var zeros = 0
    input.forEach {
        dial.move(it.first, it.second)
        if (dial.pos == 0) zeros++
    }
    println(zeros)

    dial = Dial(99, 50)
    zeros = 0
    input.forEach { input ->
        repeat(input.second) {
            dial.move(input.first, 1)
            if (dial.pos == 0) zeros++
        }
    }
    println(zeros)
}
