package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Input" {
        Solution.input.shouldHaveSize(9)
        Solution.input.first() shouldBe (190L to listOf(10L, 19L))
    }

    "Task" {
        with(Task(10, listOf(3, 4), 2)) {
            process(Task.Op.ADD) shouldBe Task(10, listOf(4), 5)
            process(Task.Op.MUL) shouldBe Task(10, listOf(4), 6)
        }

        Task(0, listOf(1), 1).process(Task.Op.ADD) shouldBe null
        Task(0, emptyList(), 0).process(Task.Op.ADD) shouldBe null

        Task(100, listOf(1), 1).process(Task.Op.CAT) shouldBe Task(100, emptyList(), 11)
    }

    "Part1" {
        Solution.part1() shouldBe 3749
    }

    "Part2" {
        Solution.part2() shouldBe 11387
    }
})
