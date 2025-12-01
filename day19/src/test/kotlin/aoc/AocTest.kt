package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.regex.shouldMatch
import io.kotest.matchers.regex.shouldNotMatch
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Regex" {
        with(Solution()) {
            regex shouldMatch "r"
            regex shouldNotMatch "x"
            regex shouldMatch "rrr"
        }
    }
    "Part 1" {
        Solution().part1 shouldBe 6
    }

    "Part 2" {
        Solution().part2 shouldBe (6 to 1)
    }
})
