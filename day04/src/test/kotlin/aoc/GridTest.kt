package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class GridTest : StringSpec({
    val test = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent()

    "Simple" {
        with(
            Grid(
                """
                123
                456
                789
                """.trimIndent()
            )
        ) {
            get(0, 0) shouldBe '1'
            get(0, 1) shouldBe '4'
            get(10, 10).shouldBeNull()
        }
    }

    "Around 8" {
        val g = Grid(test)
        val size = g.size
        var accessible = 0
        repeat(size.second) { y ->
            repeat(size.first) { x ->
                if (g.get(x, y) == '@' && g.around8(x, y).count { it == '@' } < 4) {
                    println("Accessible $x, $y")
                    accessible++
                }
            }
        }
        accessible shouldBe 13
    }
})
