package aoc22

import kotlinx.coroutines.coroutineScope
import kotlin.properties.Delegates.observable

suspend fun main() = coroutineScope {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filter { it.isNotBlank() }
        .map {it.split(' ')}
        .map { it.first() to it.lastOrNull()?.toByteOrNull() }

    var x = 1L
    var strength = 0L
    var cycles by observable(0uL) { _, _, new ->
        if ((new + 20uL) % 40uL == 0uL) {
            println("$new: $x")
            strength += new.toLong() * x
        }
    }

    input.forEach {
        when (it.first) {
            "noop" -> cycles += 1uL
            "addx" -> {
                cycles += 1uL
                cycles += 1uL
                x += it.second!!
            }
            else -> throw IllegalArgumentException(it.first)
        }
    }
    println(strength)

    x = 1L
    val d = "ahoj"
    val sprite = MutableList(40) { '.' }
    repeat(40) {
        sprite[it] = if (it in x.dec()..x.inc()) '#' else '.'
    }
    println("Sprite: ${sprite.joinToString("")}")
    println()
    val fb = MutableList(40 * 6) { '.' }
    fb.chunked(40).forEach { println(it.joinToString("")) }
    var cycles2 by observable(0uL) { _, old, new ->
        println(new)
        println("Sprite: ${sprite.joinToString("")}")
        fb[old.toInt()] = sprite[old.toInt() % 40]
        fb.chunked(40).forEach { println(it.joinToString("")) }
    }
    input.forEach {line ->
        when (line.first) {
            "noop" -> cycles2 += 1uL
            "addx" -> {
                cycles2 += 1uL
                cycles2 += 1uL
                x += line.second!!
                repeat(40) {
                    sprite[it] = if (it in x.dec()..x.inc()) '#' else '.'
                }
            }
            else -> throw IllegalArgumentException(line.first)
        }
    }
}