package aoc

fun main() {
    val input = Grid(object {}.javaClass.getResource("/input.txt")!!.readText())

    val size = input.size
    var accessible = 0
    repeat(size.second) { y ->
        repeat(size.first) { x ->
            if (input.get(x, y) == '@' && input.around8(x, y).count { it == '@' } < 4) {
                println("Accessible $x, $y")
                accessible++
            }
        }
    }
    println(accessible)
}
