package aoc

class Solution(resName: String = "input") {
    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText()
        .trim()
        .split(' ')
        .map { it.toLong() }
        .toMutableList()

    fun blink() {
        val new = buildList<Long> {
            with(input) {
                forEach { x ->
                    if (x == 0L) {
                        add(1)
                    } else if ("$x".length % 2 == 0) {
                        val s = "$x"
                        add(s.take(s.length / 2).toLong())
                        add(s.drop(s.length / 2).toLong())
                    } else {
                        add(x * 2024)
                    }
                }
            }
        }
        with(input) {
            clear()
            addAll(new)
        }
    }

    val part1 by lazy {
        repeat(25) { blink() }
        input.size
    }

    val part2 by lazy {
        repeat(75) { blink() }
        input.size
    }
}
