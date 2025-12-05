package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class JoltTest : StringSpec({
    "987654321111111" {
        println(testCase.name.name)
        testCase.name.name.map { it.digitToInt() }.jolts shouldBe 98
    }

    "811111111111119" {
        testCase.name.name.map { it.digitToInt() }.jolts shouldBe 89
    }

    "234234234234278" {
        testCase.name.name.map { it.digitToInt() }.jolts shouldBe 78
    }

    "818181911112111" {
        testCase.name.name.map { it.digitToInt() }.jolts shouldBe 92
    }
})
