package aoc22

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

sealed interface Node {
    val name: String
    suspend fun size(): Deferred<ULong>
    val children: MutableIterable<Node>?
    suspend fun toString(indent: Int): String
}

data class Dir(
    override val name: String,
    override val children: MutableCollection<Node> = mutableSetOf()
) : Node {
    override suspend fun size(): Deferred<ULong> = coroutineScope {
        async {
            children.map { it.size() }.awaitAll().sum()
        }
    }

    override suspend fun toString(indent: Int): String = buildString {
        repeat(indent) { append(' ') }
        append("[$name] ${size().await()}")
        append('\n')
        children.filterIsInstance<Dir>().forEach {
            append(it.toString(indent + 1))
        }
        children.filterIsInstance<File>().forEach {
            append(it.toString(indent + 1))
            append('\n')
        }
    }

    fun allSubdirs(dirs: MutableCollection<Dir> = mutableSetOf()): Iterable<Dir> {
        children.filterIsInstance<Dir>().flatMap {
            it.allSubdirs(dirs)
        }
        dirs += children.filterIsInstance<Dir>()
        return dirs
    }
}

data class File(override val name: String, val size: ULong) : Node {
    override suspend fun size(): Deferred<ULong> = coroutineScope {
        async {
            size
        }
    }

    override val children = null

    override suspend fun toString(indent: Int) = buildString {
        repeat(indent) { append(' ') }
        append("$name $size")
    }
}

suspend fun main() = coroutineScope {
    val root = Dir("/")
    val path = mutableListOf(root)
    object {}.javaClass.getResource("/input.txt")!!
        .readText()
        .lineSequence()
        .filter { it.isNotBlank() }
        .drop(1) // $ cd /
        .forEach {
            val line = it.split(' ')
            when (line.first()) {
                "$" -> {
                    when (line[1]) {
                        "cd" -> {
//                            println(line)
                            when (line[2]) {
                                "/" -> path.apply {
                                    clear()
                                    add(root)
                                }

                                ".." -> path.removeLast()
                                else -> path += path.last().children.filterIsInstance<Dir>()
                                    .first { dir -> dir.name == line[2] }
                            }
//                            println(path.joinToString("/") { it.name })
                        }

                        "ls" -> {}
                        else -> throw IllegalStateException(line[1])
                    }
                }

                else -> path.last().children += when (line.first()) {
                    "dir" -> Dir(line.last())
                    else -> File(line.last(), line.first().toULong())
                }
            }
        }
//    root.children.clear()
//    root.children += Dir("a").apply {
//        children += Dir("b").apply {
//            children += Dir("a")
//        }
//    }
//    println(root.toString(0))
//    println(root.allSubdirs().map { it.name })
    println(root.allSubdirs().map { it.size().await() }.filter { it <= 100000uL }.sum())

    val total = 70_000_000uL
    val required = 30_000_000uL
    val used = root.size().await()
    println(
        root.allSubdirs().map { it.size().await() }.sortedBy { it }.first { used - it < total - required }
    )
}