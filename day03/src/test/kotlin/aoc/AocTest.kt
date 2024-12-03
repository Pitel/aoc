package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import io.kotest.matchers.string.shouldNotMatch

class AocTest : StringSpec({
    "Regex" {
        "mul(44,46)" shouldMatch Solution.MUL
        "mul(123,4)" shouldMatch Solution.MUL
        "mul(4*" shouldNotMatch Solution.MUL.pattern
        "mul(6,9!" shouldNotMatch Solution.MUL.pattern
        "?(12,34)" shouldNotMatch Solution.MUL.pattern
        "mul ( 2 , 4 )" shouldNotMatch Solution.MUL.pattern
    }
    "Part 1" { Solution.part1 shouldBe 161 }
    "Part 2" { Solution.part2 shouldBe 48 }
})
