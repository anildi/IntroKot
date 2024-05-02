package ttl.introkot.student.tasks

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ttl.introkot.solutions.total
import java.time.LocalTime

/**
 * @author whynot
 */

class Order(val price: Double, quantity: Int)

//TODO task1
// Write an extension function for Order called total
// that will calculate the total for the Order (price * quantity).
// You can also change the Order class if required.


class ExtensionTasks {
    companion object {
        /****************************************task 1********************************************/
        fun task1(): Double {
            val order = Order(22.5, 10)
            //TODO - Make this code compile.
            // See the TODO comment above
//            val totalprice = order.total()
//            return totalprice

            TODO()

        }

        /****************************************task 2********************************************/
        fun withManyArgs(arg1: Int, arg2: Double, arg3: Int = 0, arg4: String): String {
            return "boo"
        }

        //TODO
        //Finish the declaration of the argTakingLambda method to take a function of the
        // same type as withManyArgs
//        fun argTakingLambda(fn: ) : String{
//            return fn(10, 22.2, 3, "handle")
//        }

        //TODO
        fun task2(): String {
            //Make this code compile
//            val result = argTakingLambda(::withManyArgs)
//            return result

            TODO()
        }

        /****************************************task 3********************************************/


        fun doSomeWork(func: (String, String) -> String): String {
            val funcResult = func("abc", "def")

            return funcResult
        }

        //TODO. Call doSomeWork with a lambda and return the result.
        // Look at testTask3 to see what the lambda should do.
        fun task3(): String {
            TODO()
        }

    }
}

class TestFunctionalTasks {
    @Test
    fun testTask1() {
        val total = ExtensionTasks.task1()
        assertEquals(225, total)
    }


    @Test
    fun testTask2() {
        val result = ExtensionTasks.task2()
        assertEquals("boo", result)
    }

    @Test
    fun testTask3() {
        val result = ExtensionTasks.task3()
        assertEquals("defabc", result)
    }
}
