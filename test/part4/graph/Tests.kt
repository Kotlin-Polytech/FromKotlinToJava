package part4.graph

import org.junit.Test
import org.junit.Assert.*
import java.util.*

class Tests {

    @Test
    fun bfs() {
        val graph = Graph()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.addVertex("E")
        graph.addVertex("F")
        graph.addVertex("G")
        graph.addVertex("H")
        graph.connect("A", "B")
        graph.connect("B", "C")
        graph.connect("B", "D")
        graph.connect("C", "E")
        graph.connect("D", "F")
        graph.connect("C", "F")
        graph.connect("G", "H")
        assertEquals(0, graph.bfs("A", "A"))
        assertEquals(3, graph.bfs("A", "F"))
        assertEquals(2, graph.bfs("E", "F"))
        assertEquals(3, graph.bfs("E", "D"))
        assertEquals(1, graph.bfs("H", "G"))
        assertEquals(-1, graph.bfs("H", "A"))
    }


    @Test
    fun dfs() {
        val graph = Graph()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.addVertex("D")
        graph.addVertex("E")
        graph.addVertex("F")
        graph.addVertex("G")
        graph.addVertex("H")
        graph.connect("A", "B")
        graph.connect("B", "C")
        graph.connect("B", "D")
        graph.connect("C", "E")
        graph.connect("D", "F")
        graph.connect("C", "F")
        graph.connect("G", "H")
        assertEquals(0, graph.dfs("A", "A"))
        assertEquals(1, graph.dfs("A", "B"))
        assertEquals(2, graph.dfs("A", "C"))
        assertEquals(2, graph.dfs("B", "F"))
        assertEquals(3, graph.dfs("A", "F"))
        assertEquals(2, graph.dfs("E", "F"))
        assertEquals(3, graph.dfs("E", "D"))
        assertEquals(1, graph.dfs("H", "G"))
        assertEquals(-1, graph.dfs("H", "A"))
    }

    @Test
    fun bfsLong() {
        val graph = Graph()
        val vertexNumber = 20000
        for (i in 1..vertexNumber) {
            val name = i.toString()
            graph.addVertex(name)
        }
        val random = Random()
        for (i in 1..vertexNumber) {
            val name = i.toString()
            for (j in 1..Math.sqrt(vertexNumber.toDouble()).toInt()) {
                val another = random.nextInt(vertexNumber) + 1
                if (another == i) continue
                graph.connect(name, another.toString())
            }
        }
        val start = random.nextInt(vertexNumber) + 1
        val finish = random.nextInt(vertexNumber) + 1
        val startTime = Calendar.getInstance().timeInMillis
        graph.bfs(start.toString(), finish.toString())
        val endTime = Calendar.getInstance().timeInMillis
        println("Start: $startTime End: $endTime Time spent: ${endTime - startTime}")
    }

    @Test
    fun dfsLong() {
        val graph = Graph()
        val vertexNumber = 16
        for (i in 1..vertexNumber) {
            val name = i.toString()
            graph.addVertex(name)
        }
        val random = Random()
        for (i in 1..vertexNumber) {
            val name = i.toString()
            for (j in 1..Math.sqrt(vertexNumber.toDouble()).toInt()) {
                val another = random.nextInt(vertexNumber) + 1
                if (another == i) continue
                graph.connect(name, another.toString())
            }
        }
        val start = random.nextInt(vertexNumber) + 1
        val finish = random.nextInt(vertexNumber) + 1
        val expectedResult = run {
            val startTime = Calendar.getInstance().timeInMillis
            val result = graph.dfs(start.toString(), finish.toString())
            val endTime = Calendar.getInstance().timeInMillis
            println("Start: $startTime End: $endTime Time spent: ${endTime - startTime}")
            result
        }
        val actualResult = run {
            val startTime = Calendar.getInstance().timeInMillis
            val result = graph.dfsMultiThread(start.toString(), finish.toString())
            val endTime = Calendar.getInstance().timeInMillis
            println("Start: $startTime End: $endTime Time spent: ${endTime - startTime}")
            result
        }
        assertEquals(expectedResult, actualResult)
    }
}