package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }

    fun Sequence<String>.magic() =
        map { l -> l.mapNotNull { c -> c.digitToIntOrNull() } }.sumOf { 10 * it.first() + it.last() }

    println(input.magic())

    println(
        input.map {
            it
                .replace("one", "o1e")
                .replace("two", "t2o")
                .replace("three", "t3e")
                .replace("four", "f4r")
                .replace("five", "f5e")
                .replace("six", "s6x")
                .replace("seven", "s7n")
                .replace("eight", "e8t")
                .replace("nine", "n9e")
        }.magic()
    )
}
