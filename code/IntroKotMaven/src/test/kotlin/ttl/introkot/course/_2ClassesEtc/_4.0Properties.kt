package ttl.introkot.course._2ClassesEtc

import org.junit.jupiter.api.Test
import java.awt.Rectangle

/**
 * @author whynot
 */
class Circle(var radius: Int, var x: Int, var y: Int) {
    val area: Double
        get() {
            return Math.PI * radius * radius
        }

    fun get(): Double {
        return 22.3
    }

    //    val area : Double = Math.PI * radius * radius
    val circumference: Double = 2 * Math.PI * radius
}

class CircleToo(radius: Int, var x: Int, var y: Int) {
    var radius: Int = radius
        set(value) {
            field = value
            area = Math.PI * radius * radius
            circumference = 2 * Math.PI * radius
        }
    var area = Math.PI * radius * radius

    //    val area : Double = Math.PI * radius * radius
    var circumference: Double = 2 * Math.PI * radius
}

val tn
    get() = Thread.currentThread().name

fun tname() = Thread.currentThread().name

//Note - example of custom *and* private setter
class CustomSet {
    var requestCount = 0
        private set(value) {
            if (value < 5) {
                println("Setting counter: $value")
                field = value
            }
        }

    fun processRequest() {
        println("processRequest in thread: ${tname()}")
        requestCount++
        //process request
    }
}


class _4TestCircle {
    @Test
    fun doCircleTest() {
        val r1 = Circle(20, 10, 10);
        println("${r1.area}")

        r1.radius = 60
        println("${r1.area}")

        val area = r1.area

        println("${r1.circumference}")


    }

    @Test
    fun doCircleTooTest() {
        val r2 = CircleToo(20, 10, 10)
        println("${r2.area}")
        r2.radius = 60
        println("${r2.area}")
    }
    @Test
    fun doSetterTest() {
        val cs = CustomSet()
//        cs.requestCount = 3

        cs.processRequest();
        cs.processRequest();

    }
}

