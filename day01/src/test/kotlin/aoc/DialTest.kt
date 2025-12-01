package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DialTest : StringSpec({
    "Start" {
        Dial(99, 50).pos shouldBe 50
    }

    "Left from start" {
        Dial(99, 50).apply { left(1) }.pos shouldBe 49
    }

    "Right from start" {
        Dial(99, 50).apply { right(1) }.pos shouldBe 51
    }

    "Left from 0" {
        Dial(99, 0).apply { left(1) }.pos shouldBe 99
    }

    "Right to 0" {
        Dial(99, 99).apply { right(1) }.pos shouldBe 0
    }
})
