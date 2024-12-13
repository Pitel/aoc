package aoc

class Solution(resName: String = "input") {
    val input = object {}.javaClass.getResource("/$resName.txt")!!
        .readText().trim().map { it.digitToInt() }

    val chunked = input.chunked(2)

    val disk = IntArray(input.sum()) { FREE }

    init {
        var offset = 0
        chunked.forEachIndexed { i, pair ->
            val file = pair.first()
            val free = pair.getOrElse(1) { 0 }
            repeat(file) { disk[offset + it] = i }
            offset += file + free
        }
    }

    fun defrag() {
        disk.forEachIndexed { i, block ->
            if (block == FREE) {
                val x = disk.indexOfLast { it != FREE }
                disk[i] = disk[x]
                disk[x] = FREE
            }
        }
        val last = disk.last()
        disk[disk.lastIndex] = FREE
        disk[disk.indexOfFirst { it == FREE }] = last
    }

    fun defrag2() {
        chunked.indices.reversed().forEach { i ->
            val size = chunked[i].first()
            val fileStart = disk.indexOfFirst { it == i }
            val freeStart = disk.asList().windowed(size).indexOfFirst { it.all { it == FREE } }
            if (freeStart != -1 && fileStart > freeStart) {
                disk.forEachIndexed { j, x -> if (x == i) disk[j] = FREE }
                repeat(size) { disk[freeStart + it] = i }
            }
        }
    }

    val checksum get() = disk.mapIndexed { i, id -> if (id != FREE) i * id.toLong() else 0 }.sum()

    val part1 get() = checksum
    val part2 get() = checksum

    companion object {
        const val FREE = -1
    }
}
