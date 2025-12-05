package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class JoltTest : StringSpec({
    "987654321111111" {
        testCase.name.name.map { it.digitToInt() }.jolts(2) shouldBe 98
        testCase.name.name.map { it.digitToInt() }.jolts(12) shouldBe 987654321111
    }

    "811111111111119" {
        testCase.name.name.map { it.digitToInt() }.jolts(2) shouldBe 89
        testCase.name.name.map { it.digitToInt() }.jolts(12) shouldBe 811111111119
    }

    "234234234234278" {
        testCase.name.name.map { it.digitToInt() }.jolts(2) shouldBe 78
        testCase.name.name.map { it.digitToInt() }.jolts(12) shouldBe 434234234278
    }

    "818181911112111" {
        testCase.name.name.map { it.digitToInt() }.jolts(2) shouldBe 92
        testCase.name.name.map { it.digitToInt() }.jolts(12) shouldBe 888911112111
    }
})
