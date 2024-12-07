package aoc

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.isActive

@OptIn(DelicateCoroutinesApi::class)
object Solution {
    val input = object {}.javaClass.getResource("/input.txt")!!.readText().lines()
        .filterNot { it.isEmpty() }
        .map {
            val split = it.split(':')
            split.first().toLong() to split.last().trim().split(' ').map { it.toLong() }
        }

    suspend fun solve(vararg ops: Task.Op) = input.map {
        GlobalScope.async {
            var solved = false
            val tasks = mutableListOf<Task>(Task(it.first, it.second))
            while (isActive && !solved && tasks.isNotEmpty()) {
                val task = tasks.removeFirst()
                if (task.numbers.isEmpty()) {
                    if (task.target == task.sum) {
                        solved = true
                        break
                    }
                } else {
                    ops.forEach { task.process(it)?.let { tasks.add(0, it) } }
                }
            }
            if (solved) it.first else 0
        }
    }.awaitAll().sum()

    suspend fun part1() = solve(Task.Op.ADD, Task.Op.MUL)
    suspend fun part2() = solve(*Task.Op.entries.toTypedArray())
}
