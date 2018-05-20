package part4.graph

import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.Future

class Graph {
    private data class Vertex(val name: String) {
        val neighbors = mutableSetOf<Vertex>()
    }

    private val vertices = mutableMapOf<String, Vertex>()

    private operator fun get(name: String) = vertices[name] ?: throw IllegalArgumentException()

    fun addVertex(name: String) {
        vertices[name] = Vertex(name)
    }

    private fun connect(first: Vertex, second: Vertex) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun connect(first: String, second: String) = connect(this[first], this[second])

    /**
     * Пример
     *
     * По двум вершинам рассчитать расстояние между ними = число дуг на самом коротком пути между ними.
     * Вернуть -1, если пути между вершинами не существует.
     *
     * Используется поиск в ширину
     */
    fun bfs(start: String, finish: String) = bfs(this[start], this[finish])

    private fun bfs(start: Vertex, finish: Vertex): Int {
        val queue = ArrayDeque<Vertex>()
        queue.add(start)
        val visited = mutableMapOf(start to 0)
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            val distance = visited[next]!!
            if (next == finish) return distance
            for (neighbor in next.neighbors) {
                if (neighbor in visited) continue
                visited.put(neighbor, distance + 1)
                queue.add(neighbor)
            }
        }
        return -1
    }

    /**
     * Пример
     *
     * По двум вершинам рассчитать расстояние между ними = число дуг на самом коротком пути между ними.
     * Вернуть -1, если пути между вершинами не существует.
     *
     * Используется поиск в глубину
     */
    fun dfs(start: String, finish: String): Int = dfs(this[start], this[finish], setOf()) ?: -1

    private fun dfs(start: Vertex, finish: Vertex, visited: Set<Vertex>): Int? =
            if (start == finish) 0
            else {
                val min = start.neighbors.filter { it !in visited }
                        .map { dfs(it, finish, visited + start) }
                        .filterNotNull().min()
                if (min == null) null else min + 1
            }

    fun dfsMultiThread(start: String, finish: String): Int {
        val startVertex = this[start]
        val finishVertex = this[finish]
        val startNeighbors = startVertex.neighbors
        val executor = Executors.newFixedThreadPool(startNeighbors.size)
        val results = mutableMapOf<Int, Future<Int>>()
        println("Total executors: ${startNeighbors.size}")
        for ((index, neighbor) in startNeighbors.withIndex()) {
            results[index] = executor.submit<Int> {
                val startTime = Calendar.getInstance().timeInMillis
                val result = dfs(neighbor, finishVertex, setOf(startVertex)) ?: -1
                val endTime = Calendar.getInstance().timeInMillis
                println("Exec ${index + 1} Start: $startTime End: $endTime Time spent: ${endTime - startTime}")
                result
            }
        }
        val min = results.values.map { it.get() }.filter { it >= 0 }.min()
        if (min != null) return min + 1
        else return -1
    }
}
