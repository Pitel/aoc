package aoc

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class IdTest : StringSpec({
    "11-22" {
        (11uL..22uL).invalids1 shouldContainExactlyInAnyOrder listOf(11uL, 22uL)
        (11uL..22uL).invalids2 shouldContainExactlyInAnyOrder listOf(11uL, 22uL)
    }

    "95-115" {
        (95uL..115uL).invalids1 shouldContainExactlyInAnyOrder listOf(99uL)
        (95uL..115uL).invalids2 shouldContainExactlyInAnyOrder listOf(99uL, 111uL)
    }

    "998-1012" {
        (998uL..1012uL).invalids1 shouldContainExactlyInAnyOrder listOf(1010uL)
        (998uL..1012uL).invalids2 shouldContainExactlyInAnyOrder listOf(999uL, 1010uL)
    }

    "1188511880-1188511890" {
        (1188511880uL..1188511890uL).invalids1 shouldContainExactlyInAnyOrder listOf(1188511885uL)
        (1188511880uL..1188511890uL).invalids2 shouldContainExactlyInAnyOrder listOf(1188511885uL)
    }

    "222220-222224" {
        (222220uL..222224uL).invalids1 shouldContainExactlyInAnyOrder listOf(222222uL)
        (222220uL..222224uL).invalids2 shouldContainExactlyInAnyOrder listOf(222222uL)
    }

    "1698522-1698528" {
        (1698522uL..1698528uL).invalids1.shouldBeEmpty()
        (1698522uL..1698528uL).invalids2.shouldBeEmpty()
    }

    "446443-446449" {
        (446443uL..446449uL).invalids1 shouldContainExactlyInAnyOrder listOf(446446uL)
        (446443uL..446449uL).invalids2 shouldContainExactlyInAnyOrder listOf(446446uL)
    }

    "38593856-38593862" {
        (38593856uL..38593862uL).invalids1 shouldContainExactlyInAnyOrder listOf(38593859uL)
        (38593856uL..38593862uL).invalids2 shouldContainExactlyInAnyOrder listOf(38593859uL)
    }

    "565653-565659" {
        (565653uL..565659uL).invalids1.shouldBeEmpty()
        (565653uL..565659uL).invalids2 shouldContainExactlyInAnyOrder listOf(565656uL)
    }
})
