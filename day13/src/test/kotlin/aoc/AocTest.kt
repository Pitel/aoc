package aoc

import aoc.Solution.Machine
import aoc.Solution.Machine.Companion.plus
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.sequences.shouldHaveSize
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Regex" {
        Solution.NUMBERS_REGEX.find("Bla bla 123 bla bla 456 bla bla").shouldNotBeNull().also {
            it.groupValues shouldBe listOf(
                "123 bla bla 456",
                "123",
                "456"
            )
        }
        Solution.NUMBERS_REGEX.find("123").shouldBeNull()
        Solution.NUMBERS_REGEX.find("Bla bla 123 bla bla").shouldBeNull()
        Solution.NUMBERS_REGEX.find("kjgkljhljkhljk").shouldBeNull()
    }

    "Input" {
        Solution().input.run {
            shouldHaveSize(4)
            last() shouldBe Machine(69L to 23L, 27L to 71L, 18641L to 10279L)
        }
    }

    "Adding pairs" {
        (1L to 2L) + (3L to 4L) shouldBe (4L to 6L)
    }

    "Min price" {
        Machine(5L to 5L, 1L to 1L, 5L to 5L).minPrice shouldBe 3L
        Machine(2L to 2L, 1L to 1L, 1L to 1L).minPrice shouldBe 1L
        Machine(2L to 2L, 1L to 1L, 1L to 2L).minPrice shouldBe Long.MAX_VALUE
        Solution().input.first().minPrice shouldBe 280L
    }

    "Part 1" {
        Solution().part1() shouldBe 480
    }

//    "Part 2" {
//        Solution().part2 shouldBe 31
//    }
})
