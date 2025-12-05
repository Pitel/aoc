package aoc

class Grid(input: String) {
    private val map = input.lines()
    val size = map.first().length to map.size

    init {
        println(map)
    }

    fun get(x: Int, y: Int) = map.getOrNull(y)?.getOrNull(x)

    fun around8(x: Int, y: Int) = buildString(8) {
        get(x - 1, y - 1)?.let { append(it) }
        get(x, y - 1)?.let { append(it) }
        get(x + 1, y - 1)?.let { append(it) }
        get(x - 1, y)?.let { append(it) }
        get(x + 1, y)?.let { append(it) }
        get(x - 1, y + 1)?.let { append(it) }
        get(x, y + 1)?.let { append(it) }
        get(x + 1, y + 1)?.let { append(it) }
    }
}
