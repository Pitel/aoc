package aoc

import java.util.Collections

class Dial(private val max: Int, start: Int) {
    private val dial = List(max + 1) { it }

    val pos get() = dial.first()

    init {
        while (pos != start) right(1)
    }

    fun left(steps: Int) {
        Collections.rotate(dial, steps)
    }

    fun right(steps: Int) {
        Collections.rotate(dial, -steps)
    }

    fun move(direction: Direction, steps: Int) = when (direction) {
        Direction.L -> left(steps)
        Direction.R -> right(steps)
    }

    enum class Direction {
        L,
        R
    }
}
