package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Regex" {
        Solution.REGEX.find("p=1,65 v=-5,-84").shouldNotBeNull().also {
            it.groupValues shouldBe listOf(
                "1,65 v=-5,-84",
                "1",
                "65",
                "-5",
                "-84"
            )
        }
    }

    "Input" {
        with(Solution(W, H).input) {
            Solution.debug(W, H, this)
            println()
            first() shouldBe Robot(0 to 4, 3 to -3)
        }
    }

    "Move" {
        with(Robot(2 to 4, 2 to -3)) {
            move(1, W, H).p shouldBe (4 to 1)
            move(2, W, H).p shouldBe (6 to 5)
            move(3, W, H).p shouldBe (8 to 2)
            move(4, W, H).p shouldBe (10 to 6)
            move(5, W, H).p shouldBe (1 to 3)
        }
    }

    "Part 1" {
        Solution(11, 7).part1 shouldBe 12
    }

//    "Part 2" {
//        Solution(11, 7).part2 shouldBe 31
//    }
}) {
    private companion object {
        private const val W = 11
        private const val H = 7
    }
}
