package aoc

data class Task(
    val target: Long,
    val numbers: List<Long>,
    val sum: Long = 0
) {
    enum class Op {
        ADD,
        MUL,
        CAT
    }

    fun process(operation: Op): Task? {
//        println("$this $operation")
        if (numbers.isEmpty()) return null
        val sum = when (operation) {
            Op.ADD -> sum + numbers.first()
            Op.MUL -> sum * numbers.first()
            Op.CAT -> "$sum${numbers.first()}".toLong()
        }
        return if (sum > target) {
            null
        } else {
            copy(
                numbers = numbers.drop(1),
                sum = sum
            )
        }
    }
}
