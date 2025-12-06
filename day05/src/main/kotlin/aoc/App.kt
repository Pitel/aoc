package aoc

fun main() {
    val input = with(
        object {}.javaClass.getResource("/input.txt")!!.readText().lineSequence().filter { it.isNotEmpty() }
    ) {
        val ranges = mutableListOf<LongRange>()
        val ingredients = mutableListOf<Long>()
        forEach {
            try {
                ingredients += it.toLong()
            } catch (e: Exception) {
                ranges += it.split('-').map { it.toLong() }.let { it.first()..it.last() }
            }
        }
        ranges.toList() to ingredients.toList()
    }
//    println(input)

    println(
        input.second.count { ingredient ->
            input.first.any { ingredient in it }
        }
    )

    input.first.let { ranges ->
        var count = 0L
        ((ranges.minOf { it.first })..ranges.maxOf { it.last }).forEach { id ->
            if (ranges.any { it.contains(id) }) count++
        }
        println(count)
    }
}
