package aoc

import aoc.Solution.Companion.pairs
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Input" {
        Solution("simple2").antennas shouldBe mapOf(
            'a' to listOf(
                4 to 3,
                5 to 5
            )
        )
        Solution("simple3").antennas shouldBe mapOf(
            'a' to listOf(
                4 to 3,
                8 to 4,
                5 to 5
            )
        )
    }

    "Combinations" {
        listOf(1, 2, 3, 4).pairs.toList() shouldBe listOf(1 to 2, 1 to 3, 1 to 4, 2 to 3, 2 to 4, 3 to 4)
    }

    "Antinodes" {
        Solution("simple2").antinodes(false).shouldContainExactlyInAnyOrder(setOf(3 to 1, 6 to 7))
        Solution("simple3").antinodes(false).shouldContainExactlyInAnyOrder(setOf(3 to 1, 6 to 7, 0 to 2, 2 to 6))
        Solution("harmonics").antinodes(true).shouldContainExactlyInAnyOrder(
            setOf(
                5 to 0,
                6 to 2,
                9 to 3,
                2 to 4,
                3 to 6,
                4 to 8,
                0 to 0,
                3 to 1,
                1 to 2
            )
        )
    }

    "Parts" {
        with(Solution()) {
            part1 shouldBe 14
            part2 shouldBe 34
        }
    }
})
