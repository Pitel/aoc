package aoc

data class Robot(
    val p: Pair<Int, Int>,
    val v: Pair<Int, Int>,
) {
    fun move(secs: Int, w: Int, h: Int) = copy(
        p = run {
            var x = (p.first + v.first * secs) % w
            if (x < 0) x += w
            var y = (p.second + v.second * secs) % h
            if (y < 0) y += h
            x to y
        }
    )
}
