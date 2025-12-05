package aoc

fun List<Int>.jolts(batteries: Int): Int {
    val jolts = StringBuilder(2)

    var digits = this
    println(digits)
    var max = if (digits.size > 1) digits.dropLast(1).max() else digits.last()
    jolts.append(max)

    digits = digits.dropWhile { it != max }.drop(1)
    println(digits)
    max = digits.max()
    jolts.append(max)

    return "$jolts".toInt()
}

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .map { it.map { it.digitToInt() } }

    println(input.map { it.jolts }.sum())
//    println(input.sumOf { it.invalids2.sum() })
}
