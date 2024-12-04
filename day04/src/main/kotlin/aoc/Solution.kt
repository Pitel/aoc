package aoc

class Solution(
    private val crossword: List<CharSequence> = object {}.javaClass.getResource("/input.txt")!!.readText().lines()
) {
    fun solve(
        word: String,
        cross: Boolean
    ) = crossword.mapIndexed { y, l ->
        l.mapIndexed { x, c ->
            if (cross) {
                require(word.length == 3) { "Only 3 letter words" }
                val d1 = buildString {
                    crossword.getOrNull(y - 1)?.getOrNull(x - 1)?.let { append(it) }
                    crossword.getOrNull(y)?.getOrNull(x)?.let { append(it) }
                    crossword.getOrNull(y + 1)?.getOrNull(x + 1)?.let { append(it) }
                }
                val d2 = buildString {
                    crossword.getOrNull(y - 1)?.getOrNull(x + 1)?.let { append(it) }
                    crossword.getOrNull(y)?.getOrNull(x)?.let { append(it) }
                    crossword.getOrNull(y + 1)?.getOrNull(x - 1)?.let { append(it) }
                }
                if ((d1 == word || d1 == word.reversed()) && (d2 == word || d2 == word.reversed())) 1 else 0
            } else {
                val w = List(8) { StringBuilder(word.length) }
                word.indices.forEach { i ->
                    crossword.getOrNull(y)?.getOrNull(x + i)?.let { w[0].append(it) }
                    crossword.getOrNull(y)?.getOrNull(x - i)?.let { w[1].append(it) }
                    crossword.getOrNull(y + i)?.getOrNull(x)?.let { w[2].append(it) }
                    crossword.getOrNull(y - i)?.getOrNull(x)?.let { w[3].append(it) }
                    crossword.getOrNull(y + i)?.getOrNull(x + i)?.let { w[4].append(it) }
                    crossword.getOrNull(y + i)?.getOrNull(x - i)?.let { w[5].append(it) }
                    crossword.getOrNull(y - i)?.getOrNull(x + i)?.let { w[6].append(it) }
                    crossword.getOrNull(y - i)?.getOrNull(x - i)?.let { w[7].append(it) }
                }
                w.count { "$it" == word }
            }
        }.sum()
    }.sum()

    val part1 by lazy { solve("XMAS", false) }
    val part2 by lazy { solve("MAS", true) }
}
