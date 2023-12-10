package aoc

fun main() {
    val input = object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()

    val instructions = input.first()
    println(instructions)

    val regex = """^([A-Z]+) = \(([A-Z]+), ([A-Z]+)\)$""".toRegex()
    val tree = input.drop(2).map {
        val match = regex.matchEntire(it)
        match!!.groupValues[1] to (match.groupValues[2] to match.groupValues[3])
    }.toMap()
    println(tree)

    var i = 0
    var next = "AAA"
    while (next != "ZZZ") {
        val instruction = instructions[i % instructions.length]
        //println("$i $next $instruction")
        next = when (instruction) {
            'L' -> tree[next]!!.first
            'R' -> tree[next]!!.second
            else -> error("Unknown instruction $instruction")
        }
        i++
    }
    println(i)
}
