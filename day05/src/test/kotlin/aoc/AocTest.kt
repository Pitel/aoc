package aoc

import aoc.Solution.mid
import aoc.Solution.pairs
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Middle" {
        byteArrayOf(1, 2, 3).mid shouldBe 2
    }

    "Rules" {
        Solution.check(listOf(1.toByte() to 2.toByte()), listOf(1.toByte() to 2.toByte())).shouldBeTrue()
        Solution.check(listOf(1.toByte() to 2.toByte()), listOf(10.toByte() to 20.toByte())).shouldBeTrue()
        Solution.check(
            listOf(1.toByte() to 2.toByte(), 3.toByte() to 4.toByte()),
            listOf(10.toByte() to 20.toByte())
        ).shouldBeTrue()

        Solution.check(listOf(1.toByte() to 2.toByte()), listOf(2.toByte() to 1.toByte())).shouldBeFalse()
        Solution.check(
            listOf(1.toByte() to 2.toByte()),
            listOf(10.toByte() to 20.toByte(), 2.toByte() to 1.toByte())
        ).shouldBeFalse()
        Solution.check(
            listOf(1.toByte() to 2.toByte()),
            listOf(1.toByte() to 2.toByte(), 2.toByte() to 1.toByte())
        ).shouldBeFalse()
    }

    "Pairs" {
        byteArrayOf(1, 2, 3).pairs shouldBe listOf(
            1.toByte() to 2.toByte(),
            1.toByte() to 3.toByte(),
            2.toByte() to 3.toByte()
        )
    }

    "Full" {
        Solution.pages.map { pages ->
            Solution.check(pages.pairs.asIterable(), Solution.ordering)
        } shouldBe listOf(true, true, true, false, false, false)
    }

    "Part 1" { Solution.part1 shouldBe 143 }
    "Part 2" { Solution.part2 shouldBe 143 }
})
