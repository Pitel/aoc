package aoc

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@OptIn(DelicateCoroutinesApi::class)
class Solution(resName: String = "input") {
    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText().lines().filter { !it.isEmpty() }.map {
        it.map {
            try {
                it.digitToInt().toByte()
            } catch (iae: IllegalArgumentException) {
                Byte.MIN_VALUE
            }
        }
    }

    val starts = buildSet {
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, byte ->
                if (byte == (0.toByte())) {
                    add(x to y)
                }
            }
        }
    }

    val part1 = starts.map {
        GlobalScope.async {
            val tops = mutableSetOf<Pair<Int, Int>>()
            val points = mutableSetOf(it)
            while (points.isNotEmpty()) {
                val point = points.first()
                points -= point
                val x = point.first
                val y = point.second
                val height = input[y][x]
                if (height == 9.toByte()) {
                    tops += point
                } else {
                    input.getOrNull(y + 1)?.getOrNull(x)?.let { if (it == height.inc()) points += x to y + 1 }
                    input.getOrNull(y - 1)?.getOrNull(x)?.let { if (it == height.inc()) points += x to y - 1 }
                    input.getOrNull(y)?.getOrNull(x + 1)?.let { if (it == height.inc()) points += x + 1 to y }
                    input.getOrNull(y)?.getOrNull(x - 1)?.let { if (it == height.inc()) points += x - 1 to y }
                }
            }
            tops.size
        }
    }

    val part2 = starts.map {
        GlobalScope.async {
            val tops = mutableListOf<Pair<Int, Int>>()
            val points = mutableListOf(it)
            while (points.isNotEmpty()) {
                val point = points.first()
                points -= point
                val x = point.first
                val y = point.second
                val height = input[y][x]
                if (height == 9.toByte()) {
                    tops += point
                } else {
                    input.getOrNull(y + 1)?.getOrNull(x)?.let { if (it == height.inc()) points += x to y + 1 }
                    input.getOrNull(y - 1)?.getOrNull(x)?.let { if (it == height.inc()) points += x to y - 1 }
                    input.getOrNull(y)?.getOrNull(x + 1)?.let { if (it == height.inc()) points += x + 1 to y }
                    input.getOrNull(y)?.getOrNull(x - 1)?.let { if (it == height.inc()) points += x - 1 to y }
                }
            }
            tops.size
        }
    }
}
