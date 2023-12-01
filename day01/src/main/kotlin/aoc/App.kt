package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filterNot { it.isBlank() }

    println(
        input
            .map { l -> l.mapNotNull { c -> c.digitToIntOrNull() } }
            .sumOf { 10 * it.first() + it.last() }
    )

    println(
        input.sumOf { l ->
            val first = l.fold("") { acc, c ->
                (acc + c)
                    .replaceFirst("one", "1")
                    .replaceFirst("two", "2")
                    .replaceFirst("three", "3")
                    .replaceFirst("four", "4")
                    .replaceFirst("five", "5")
                    .replaceFirst("six", "6")
                    .replaceFirst("seven", "7")
                    .replaceFirst("eight", "8")
                    .replaceFirst("nine", "9")
            }
                .first { it.isDigit() }
                .digitToInt()

            val last = l.reversed().fold("") { acc, c ->
                (acc + c)
                    .replaceFirst("one".reversed(), "1")
                    .replaceFirst("two".reversed(), "2")
                    .replaceFirst("three".reversed(), "3")
                    .replaceFirst("four".reversed(), "4")
                    .replaceFirst("five".reversed(), "5")
                    .replaceFirst("six".reversed(), "6")
                    .replaceFirst("seven".reversed(), "7")
                    .replaceFirst("eight".reversed(), "8")
                    .replaceFirst("nine".reversed(), "9")
            }
                .first { it.isDigit() }
                .digitToInt()

            10 * first + last
        }
    )
}
