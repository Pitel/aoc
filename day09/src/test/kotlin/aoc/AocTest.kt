package aoc

import aoc.Solution.Companion.FREE
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Disk" {
        Solution("12345").disk shouldBe intArrayOf(0, FREE, FREE, 1, 1, 1, FREE, FREE, FREE, FREE, 2, 2, 2, 2, 2)
    }

    "Defrag" {
        Solution("12345").apply { defrag() }.disk shouldBe intArrayOf(
            0, 2, 2, 1, 1, 1, 2, 2, 2, FREE, FREE, FREE, FREE, FREE, FREE
        )
    }

    "Defrag 2" {
        Solution().apply { defrag2() }.disk shouldBe intArrayOf(
            0, 0, 9, 9, 2, 1, 1, 1, 7, 7, 7, FREE, 4, 4, FREE, 3, 3, 3, FREE, FREE, FREE, FREE, 5, 5, 5, 5, FREE, 6, 6, 6, 6, FREE, FREE, FREE, FREE, FREE, 8, 8, 8, 8, FREE, FREE
        )
    }

    "Part 1" {
        Solution().apply { defrag() }.part1 shouldBe 1928
    }

    "Part 2" {
        Solution().apply { defrag2() }.part2 shouldBe 2858
    }
})
