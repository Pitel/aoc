package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Simple" {
        Solution(object {}.javaClass.getResource("/simple.txt")!!.readText().lines()).part1 shouldBe 4
    }

    "Part 1" {
        Solution(object {}.javaClass.getResource("/xmas.txt")!!.readText().lines()).part1 shouldBe 18
    }

    "Cross" {
        Solution(object {}.javaClass.getResource("/cross.txt")!!.readText().lines()).part2 shouldBe 1
        Solution(object {}.javaClass.getResource("/cross_bad1.txt")!!.readText().lines()).part2 shouldBe 0
        Solution(object {}.javaClass.getResource("/cross_bad2.txt")!!.readText().lines()).part2 shouldBe 0
    }

    "Part 2" {
        Solution(object {}.javaClass.getResource("/xmas.txt")!!.readText().lines()).part2 shouldBe 9
    }
})
