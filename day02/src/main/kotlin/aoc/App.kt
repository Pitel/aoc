package aoc

val ULongRange.invalids1: List<ULong> get() = filter {
    """(\d+)\1""".toRegex().matchEntire("$it") != null
}

val ULongRange.invalids2: List<ULong> get() = filter {
    """(\d+)\1+""".toRegex().matchEntire("$it") != null
}

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .splitToSequence(',')
        .map { it.split('-').map { it.toULong() } }
        .map { it.first()..it.last() }

    println(input.sumOf { it.invalids1.sum() })
    println(input.sumOf { it.invalids2.sum() })
}
