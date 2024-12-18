package aoc

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class Solution(private val resName: String = "input") {
    val part1 by lazy { Computer().run() }

    suspend fun part2() = coroutineScope {
        var found = MutableStateFlow(Int.MIN_VALUE)
        val f = flow {
            var i = 0
            while (found.value == Int.MIN_VALUE) {
                emit(i++)
            }
        }
        repeat(Runtime.getRuntime().availableProcessors()) {
            f.onEach {
                val c = Computer(resName).apply { a = it }
                if (c.run() == c.program.joinToString(",")) {
                    found.value = it
                }
            }.launchIn(this)
        }
        found.first { it != Int.MIN_VALUE }
    }
}
