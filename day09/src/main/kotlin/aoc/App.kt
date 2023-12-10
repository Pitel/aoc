package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .map { it.split(' ').map { it.toInt() } }

    println(input.toList())
    println()

    println(
        input
//            .map { it.asReversed() } // Part 2
            .map {
                buildList<List<Int>> {
                    add(it)
                    while (!last().all { it == 0 }) {
                        val seq = last()
                        add(List(seq.size - 1) { i -> seq[i + 1] - seq[i] })
                    }
                }
            }
            .map {
                var n = 0
                it.reversed().forEach {
                    n += it.last()
                }
                n
            }
            .sum()
    )
}
