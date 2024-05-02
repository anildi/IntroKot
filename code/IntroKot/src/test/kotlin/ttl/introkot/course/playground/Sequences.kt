package ttl.introkot.course.playground

import org.junit.jupiter.api.Test
import ttl.introkot.course._1Intro.list
import java.util.stream.Stream

/**
 * @author whynot
 */

class Sequences {

    @Test
    fun listAsSequence() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        //This one will create a new list for each operation.
        //So we don't need a terminal operation because, in a sense,
        //each operation is a terminal operation.
        println("Doing Eager")
        val eager = list
            .filter{it % 2 == 0}
            .map{it * it}

        println("\nDoing sequence")
        //No intermediate Lists will be created.  But we need
        //a terminal operation (here we are using toList())
        //to start the processing.
        val lazy = list
            .asSequence()
            .filter{it % 2 == 0}
            .map{it * it}
            .toList()

        for(i in lazy) println(i)
    }
    /**
     * generateSequence stops at null value
     */
    @Test
    fun generator() {
        var list = generateSequence(1) {
            if (it > 3) null else it + 1
        }.toList()

        println(list)
    }

    @Test
    fun testSequenceTee() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val lazy = list
            .asSequence()
            .onEach{println("BeforeFilter: $it")}
            .filter{it % 2 == 0}
            .onEach{println("BeforeMap: $it")}
            .map{it * it}
            .onEach{println("AfterMap: $it")}
//            .toList()

        for(i in lazy) println(i)
    }
}