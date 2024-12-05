package aoc

object Solution {
    val ordering = mutableListOf<Pair<Byte, Byte>>()
    val pages = mutableListOf<ByteArray>()

    init {
        object {}.javaClass.getResource("/input.txt")!!.readText().lineSequence().forEach {
            if (it.contains('|')) {
                ordering.add(it.split('|').run { first().toByte() to last().toByte() })
            } else if (it.contains(',')) {
                pages.add(it.split(',').map { it.toByte() }.toByteArray())
            }
        }
//        println(ordering)
//        println(pages)
    }

    val ByteArray.mid get() = get(size / 2)

    val ByteArray.pairs get() = flatMapIndexed { i, p ->
        buildList {
            this@pairs.forEachIndexed { j, b ->
                if (i < j) add(p to b)
                if (i > j) add(b to p)
            }
        }
    }.distinct()

    fun check(pages: Iterable<Pair<Byte, Byte>>, rules: Iterable<Pair<Byte, Byte>>) =
        pages.all { pagesPair ->
            rules.none { pagesPair == it.second to it.first }
        }

    val part1 by lazy {
        pages.map { if (check(it.pairs.asIterable(), ordering)) it.mid else 0 }.sum()
    }
    val part2 by lazy { 0 }
}
