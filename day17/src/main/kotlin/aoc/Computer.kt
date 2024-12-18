package aoc

import kotlin.math.pow

class Computer(resName: String = "input") {
    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText()
        .lines()
        .filter { it.isNotEmpty() }

    var a = 0
    var b = 0
    var c = 0
    val program = input.last().split(' ').last().split(',').map { it.toInt() }
    var ip = 0

    fun reset() {
        a = input[0].split(' ').last().toInt()
        b = input[1].split(' ').last().toInt()
        c = input[2].split(' ').last().toInt()
        ip = 0
    }

    init {
        reset()
    }

    val Int.combo get() = when (this) {
        0, 1, 2, 3 -> this
        4 -> a
        5 -> b
        6 -> c
        7 -> TODO("Reserved")
        else -> throw IllegalArgumentException("Combo must be 0-7")
    }

    fun step(): Int? = when (Opcode.entries[program[ip]]) {
        Opcode.ADV -> {
            a /= 2.0.pow(program[ip + 1].combo).toInt()
            null
        }
        Opcode.BXL -> {
            b = b xor program[ip + 1]
            null
        }
        Opcode.BST -> {
            b = program[ip + 1].combo % 8
            null
        }
        Opcode.JNZ -> {
            if (a != 0) {
                ip = program[ip + 1] - 2 // -2 to counter the increase
            }
            null
        }
        Opcode.BXC -> {
            b = b xor c
            null
        }
        Opcode.OUT -> program[ip + 1].combo % 8
        Opcode.BDV -> {
            b = a / 2.0.pow(program[ip + 1].combo).toInt()
            null
        }
        Opcode.CDV -> {
            c = a / 2.0.pow(program[ip + 1].combo).toInt()
            null
        }
    }.also { ip += 2 }

    fun run() = buildList {
        try {
            while (true) {
                step()?.let { add(it) }
            }
        } catch (e: IndexOutOfBoundsException) {
            // Halt
        }
    }.joinToString(",")
}
