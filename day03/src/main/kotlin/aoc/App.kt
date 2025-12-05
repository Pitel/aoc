package aoc

fun List<Int>.jolts(batteries: Int): Long {
    val jolts = StringBuilder(batteries)

    var digits = this
    repeat(batteries) { bat ->
        val tail = batteries - bat - 1
        val max = digits.dropLast(tail).max()
        println("$bat ($tail): $digits > $max")
        jolts.append(max)
        digits = digits.dropWhile { it != max }.drop(1)
    }

    return "$jolts".toLong()
}

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .map { it.map { it.digitToInt() } }

    println(input.map { it.jolts(2) }.sum())
    println(input.map { it.jolts(12) }.sum())
}
