package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Part 1" {
        Solution().part1 shouldBe "4,6,3,5,6,3,5,2,1,0"
    }

    "Part 2" {
        Solution("2024").part2 shouldBe 117440
    }
})
