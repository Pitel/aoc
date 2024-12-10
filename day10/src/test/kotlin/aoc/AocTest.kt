package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.awaitAll

class AocTest : StringSpec({
    "Part 1" {
        Solution("1").part1.awaitAll().sum() shouldBe 1
        Solution("2").part1.awaitAll().sum() shouldBe 2
        Solution("3").part1.awaitAll().sum() shouldBe 4
        Solution("4").part1.awaitAll().sum() shouldBe 1 + 2
        Solution("5").part1.awaitAll().sum() shouldBe 36
    }

    "Part 2" {
        Solution("6").part2.awaitAll().sum() shouldBe 3
        Solution("3").part2.awaitAll().sum() shouldBe 13
        Solution("7").part2.awaitAll().sum() shouldBe 227
        Solution("5").part2.awaitAll().sum() shouldBe 81
    }
})
