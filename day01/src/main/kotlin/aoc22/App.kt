package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .fold(mutableListOf(0u)) { acc, i ->
            acc.apply {
                if (i.isBlank()) {
                    add(0u)
                } else {
                    this[size - 1] += i.toUInt()
                }
            }
        }

    println(input.max())
    println(input.sortedDescending().take(3).sum())
}
