package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Part 1" { Solution.part1 shouldBe 2 }
    "Part 2" { Solution.part2 shouldBe 4 }
})
