package aoc

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
    val part2 = GlobalScope.async(start = CoroutineStart.LAZY) {
        pages.filterNot { check(it.pairs.asIterable(), ordering) }.map { pages ->
            var solution = MutableStateFlow<ByteArray?>(null)
            repeat(Runtime.getRuntime().availableProcessors()) {
                launch {
                    while (solution.value == null) {
                        val shuffled = pages.asIterable().shuffled()
                        if (check(shuffled.toByteArray().pairs, ordering)) {
                            solution.value = shuffled.toByteArray()
                            break
                        }
                    }
                }
            }
            solution.filterNotNull().first().mid
        }.sum()
    }
}
