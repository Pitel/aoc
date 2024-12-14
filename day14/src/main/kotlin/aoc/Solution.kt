package aoc

class Solution(val w: Int = 101, val h: Int = 103, resName: String = "input") {
    val input = object {}.javaClass.getResource("/$resName.txt")!!.readText()
        .lines()
        .filter { it.isNotEmpty() }
        .map {
            REGEX.find(it)!!.let {
                Robot(
                    it.groupValues[1].toInt() to it.groupValues[2].toInt(),
                    it.groupValues[3].toInt() to it.groupValues[4].toInt(),
                )
            }
        }

    val part1 by lazy {
        val after100 = input.map { it.move(100, w, h) }
        debug(w, h, after100)
        val qw = w / 2
        val qh = h / 2
        println("$qw $qh")
        val q1 = after100.filter { it.p.first in 0..<qw && it.p.second in 0..<qh }.size
        val q2 = after100.filter { it.p.first in qw + 1..<w && it.p.second in 0..<qh }.size
        val q3 = after100.filter { it.p.first in 0..<qw && it.p.second in qh + 1..<h }.size
        val q4 = after100.filter { it.p.first in qw + 1..<w && it.p.second in qh + 1..<h }.size
        println("$q1 $q2 $q3 $q4")
        q1 * q2 * q3 * q4
    }
    val part2 = Unit

    companion object {
        val REGEX = """(\d+),(\d+)[^\d-]+(-?\d+),(-?\d+)""".toRegex()

        fun debug(w: Int, h: Int, robots: Iterable<Robot>) {
            val map = Array(h) { IntArray(w) { 0 } }
            robots.forEach {
                map[it.p.second][it.p.first] = map[it.p.second][it.p.first].inc()
            }
            map.forEach { line ->
                line.forEach {
                    print(if (it == 0) '.' else "$it")
                }
                println()
            }
        }
    }
}
