package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class AocTest : StringSpec({
    "Next" {
        Solution.Direction.UP.next shouldBe Solution.Direction.RIGHT
        Solution.Direction.RIGHT.next shouldBe Solution.Direction.DOWN
        Solution.Direction.DOWN.next shouldBe Solution.Direction.LEFT
        Solution.Direction.LEFT.next shouldBe Solution.Direction.UP
        Solution.Direction.UP.next.next shouldBe Solution.Direction.DOWN
        Solution.Direction.UP.next.next.next.next shouldBe Solution.Direction.UP
    }

    "Start" {
        Solution.position shouldBe (4 to 6)
    }

    "Walkthrough" {
        Solution.step()
        Solution.position shouldBe (4 to 5)
        Solution.direction shouldBe Solution.Direction.UP
        Solution.visited.shouldHaveSize(1)
        repeat(4) { Solution.step() }
        Solution.position shouldBe (4 to 1)
        Solution.direction shouldBe Solution.Direction.UP
        Solution.visited.shouldHaveSize(5)
        Solution.step()
        Solution.position shouldBe (4 to 1)
        Solution.direction shouldBe Solution.Direction.RIGHT
        Solution.visited.shouldHaveSize(5)
        Solution.step()
        Solution.position shouldBe (5 to 1)
        Solution.direction shouldBe Solution.Direction.RIGHT
        Solution.visited.shouldHaveSize(6)
    }

    "Parts" {
        Solution.part1 shouldBe 41
        Solution.part2 shouldBe 6
    }
})
