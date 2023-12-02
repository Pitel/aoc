package aoc

data class RGB(val r: Int, val g: Int, val b: Int) {
    val possible = r <= 12 && g <= 13 && b <= 14
    val power = r * g * b
}
