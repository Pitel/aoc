package aoc

object Solution {
    val input = object {}.javaClass.getResource("/input.txt")!!.readText().lines()

    lateinit var position: Pair<Int, Int>
        private set

    operator fun Pair<Int, Int>.plus(direction: Direction) =
        first + direction.move.first to second + direction.move.second

    enum class Direction(val move: Pair<Int, Int>) {
        UP(0 to -1),
        RIGHT(1 to 0),
        DOWN(0 to 1),
        LEFT(-1 to 0);

        val next get() = entries[ordinal.inc() % entries.size]
    }

    var direction = Direction.UP
        private set

    val visited = mutableListOf<Pair<Int, Int>>()
    val obstructions = mutableSetOf<Pair<Int, Int>>()

    init {
        input.forEachIndexed { y, row ->
            val x = row.indexOf('^')
            if (x != -1) position = (x to y)
        }
    }

    fun step() {
        val nextPosition = position + direction
        if (nextPosition in visited) {
            val lookAhead = nextPosition + direction
            if (input[lookAhead.second][lookAhead.first] != '#') {
                obstructions += lookAhead
            }
        }
        if (input[nextPosition.second][nextPosition.first] == '#') {
            direction = direction.next
        } else {
            visited += position
            position = nextPosition
        }
    }

    val part1 by lazy {
        try {
            while (true) step()
        } catch (ioobe: IndexOutOfBoundsException) {
            visited.distinct().size + 1
        }
    }

    val part2 by lazy { obstructions.size }
}
