package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lines()
        .filterNot { it.isBlank() }

    fun hasSymbol(x: Int, y: Int): Boolean {
        println("${input[y][x]} [$x,$y]")
        for (yoff in -1..1) {
            for (xoff in -1..1) {
                val c = input.getOrNull(y + yoff)?.getOrNull(x + xoff) ?: '.'
                if (!(c.isDigit() || c == '.')) {
                    return true
                }
            }
        }
        return false
    }

    var start = 0
    var numbers = ""
    var sum = 0

    fun numberEnds(start: Int, end: Int, y: Int) {
        if (numbers.isNotEmpty()) {
            // Number sequence finished
            println("$numbers $start-$end")
            sum += if (start == end) {
                if (hasSymbol(start, y)) {
                    numbers.toInt()
                } else {
                    0
                }
            } else if ((start until end).map { hasSymbol(it, y) }.any { it }) {
                println("^^^")
                numbers.toInt()
            } else {
                0
            }
            println()
        }
        numbers = ""
    }

    repeat(input.size) { y ->
        input[y].forEachIndexed { x, c ->
            if (c.isDigit()) {
                // Got number
                if (numbers.isEmpty()) {
                    // First number
                    start = x
                }
                numbers += c
                if (x == input[y].length - 1) {
                    // EOL
                    numberEnds(start, x, y)
                    println("EOL")
                }
            } else {
                numberEnds(start, x, y)
            }
        }
    }
    println(sum)
}
