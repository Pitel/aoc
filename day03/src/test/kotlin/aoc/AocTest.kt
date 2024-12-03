package aoc

import aoc.Solution.DO
import aoc.Solution.DONT
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import io.kotest.matchers.string.shouldNotMatch

class AocTest : StringSpec({
    "Regex" {
        "mul(44,46)" shouldMatch Solution.MUL
        "mul(123,4)" shouldMatch Solution.MUL
        "mul(4*" shouldNotMatch Solution.MUL.pattern
        "mul(6,9!" shouldNotMatch Solution.MUL.pattern
        "?(12,34)" shouldNotMatch Solution.MUL.pattern
        "mul ( 2 , 4 )" shouldNotMatch Solution.MUL.pattern
    }
    "Part 1" { Solution.part1 shouldBe 161 }
    "Part 2" { Solution.part2 shouldBe 48 }
    "Edge cases" {
        val MUL = "mul(1,1)"
        Solution.part2(MUL) shouldBe 1
        Solution.part2("$MUL$DO") shouldBe 1
        Solution.part2("$MUL$DONT") shouldBe 1
        Solution.part2("$DO$MUL") shouldBe 1
        Solution.part2("$DO$DO$MUL") shouldBe 1
        Solution.part2("$DONT$MUL") shouldBe 0
        Solution.part2("$DONT$DO$MUL") shouldBe 1
        Solution.part2("$DONT$DONT$MUL") shouldBe 0
        Solution.part2("$DO$DONT$MUL") shouldBe 0
        Solution.part2("$DO$MUL$DO$MUL") shouldBe 2
        Solution.part2("$DO$MUL$DONT$MUL") shouldBe 1
        Solution.part2("$DONT$MUL${DO}mul(2,2)") shouldBe 4
        Solution.part2("$MUL$DONT$MUL$DO$MUL") shouldBe 2
        Solution.part2("$MUL$DONT$MUL$DO$MUL") shouldBe 2
    }
})
