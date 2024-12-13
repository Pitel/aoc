package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Input" {
        Solution("1").input.shouldHaveSize(5)
        Solution("2").input.shouldHaveSize(2)
    }

    "Blinking" {
        Solution("1").apply { blink() }.input shouldBe listOf(1, 2024, 1, 0, 9, 9, 2021976)
        with(Solution("2")) {
            blink()
            input shouldBe listOf(253000, 1, 7)
            blink()
            input shouldBe listOf(253, 0, 2024, 14168)
            blink()
            input shouldBe listOf(512072, 1, 20, 24, 28676032)
            blink()
            input shouldBe listOf(512, 72, 2024, 2, 0, 2, 4, 2867, 6032)
            blink()
            input shouldBe listOf(1036288, 7, 2, 20, 24, 4048, 1, 4048, 8096, 28, 67, 60, 32)
            blink()
            input shouldBe listOf(2097446912, 14168, 4048, 2, 0, 2, 4, 40, 48, 2024, 40, 48, 80, 96, 2, 8, 6, 7, 6, 0, 3, 2)
        }
    }

    "Part 1" {
        Solution("2").part1 shouldBe 55312
    }
})
