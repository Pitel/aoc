package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Input" {
        with(Solution()) {
            w shouldBe 6
            h shouldBe 6
        }
    }

    "Neighbour" {
        with(Solution(take = 0)) {
            (0 to 0).neighbours.toList() shouldContainExactlyInAnyOrder listOf(0 to 1, 1 to 0)
            (1 to 2).neighbours.toList() shouldContainExactlyInAnyOrder listOf(1 to 3, 1 to 1, 2 to 2, 0 to 2)
            (w to h).neighbours.toList() shouldContainExactlyInAnyOrder listOf(w - 1 to h, w to h - 1)
        }
    }

    "Part 1" {
        Solution(take = 12).part1 shouldBe 22
    }

    "Part 2" {
        Solution().part2 shouldBe (6 to 1)
    }
})
