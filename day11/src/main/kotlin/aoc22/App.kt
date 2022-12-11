package aoc22

data class Monkey(
    val items: MutableList<ULong>,
    val operation: (ULong) -> ULong,
    val divisible: ULong,
    val test: (ULong) -> Int,
    var inspecting: ULong = 0uL
)

fun loadInput() = object {}.javaClass.getResource("/input.txt")!!
    .readText()
    .lines()
    .filter { it.isNotBlank() }
    .chunked(6) { monkey ->
        val line = monkey[2].split(' ').takeLast(3)
        val divisible = monkey[3].split(' ').last().toULong()
        val t = monkey[4]
        val f = monkey[5]
        Monkey(
            monkey[1].split(": ")[1].split(", ").map { it.toULong() }.toMutableList(),
            { old ->
                val first = when (line[0]) {
                    "old" -> old
                    else -> line[0].toULong()
                }
                val second = when (line[2]) {
                    "old" -> old
                    else -> line[2].toULong()
                }
                when (line[1].first()) {
                    '+' -> first + second
                    '*' -> first * second
                    else -> throw IllegalArgumentException(line[1])
                }
            },
            divisible,
            {
                if (it % divisible == 0uL) {
                    t
                } else {
                    f
                }.split(' ').last().toInt()
            }
        )
    }

fun main() {
    var monkeys = loadInput()

    repeat(20) {
        monkeys.forEachIndexed { i, monkey ->
            println("Monkey $i:")
            repeat(monkey.items.size) {
                monkey.inspecting += 1uL
                var item = monkey.items.removeFirst()
                println("  Monkey inspects an item with a worry level of $item.")
                item = monkey.operation(item)
                println("    Worry after operation: $item")
                item /= 3uL
                println("    Monkey gets bored with item. Worry level is divided by 3 to $item.")
                val target = monkey.test(item)
                println("    Item with worry level $item is thrown to monkey $target.")
                monkeys[target].items += item
            }
        }
    }

    var monkeybusiness = monkeys.map { it.inspecting }.sortedDescending().take(2)
    println(monkeybusiness.first() * monkeybusiness.last())

    monkeys = loadInput()
    val gcd = monkeys.map { it.divisible }.reduce { acc, uLong -> acc * uLong }
    repeat(10_000) {
//        println(it)
        monkeys.forEachIndexed { i, monkey ->
//            println("Monkey $i:")
            repeat(monkey.items.size) {
                monkey.inspecting += 1uL
                var item = monkey.items.removeFirst()
//                println("  Monkey inspects an item with a worry level of $item.")
                item = monkey.operation(item) % gcd
//                println("    Worry after operation: $item")
                val target = monkey.test(item)
//                println("    Item with worry level $item is thrown to monkey $target.")
                monkeys[target].items += item
            }
        }
    }
//    monkeys.forEach {
//        println(it.inspecting)
//    }
    monkeybusiness = monkeys.map { it.inspecting }.sortedDescending().take(2)
    println(monkeybusiness.first() * monkeybusiness.last())
}