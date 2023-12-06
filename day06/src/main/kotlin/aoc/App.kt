package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .map { it.split(' ').filterNot { it.isBlank() }}
        .let {
            it.first().zip(it.last())
        }
        .drop(1)
        .map {
            it.first.toInt() to it.second.toInt()
        }
    println(input)

    input.map { race ->
        val time = race.first
        val recordDistance = race.second
        (0..time).map { pressTime ->
            (time - pressTime) * pressTime
        }.count { it > recordDistance }
    }.reduce { a, b -> a * b }
        .also { println(it) }

    val input2 = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .map { it.replace(" ", "").split(':').last().toLong() }
    println(input2)

    val time = input2.first()
    val recordDistance = input2.last()
    (0..time).map { pressTime ->
        (time - pressTime) * pressTime
    }.count { it > recordDistance }.also { println(it) }
}
