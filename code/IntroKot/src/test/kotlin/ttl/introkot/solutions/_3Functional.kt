package ttl.introkot.solutions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * @author whynot
 */

class OrderSol(val price: Double, val quantity: Int)

//TODO task1
// Write an extension function for Order called total
// that will calculate the total for the Order (price * quantity).
// You can also change the Order class if required.

//Note - Extensions function
fun OrderSol.total() = price * quantity

class ExtensionTasksSol {
    companion object {
        /****************************************task 1********************************************/
        fun task1(): Double {
            val order = OrderSol(22.5, 10)
            //Make this code compile
            //See the TODO comment above
            val totalprice = order.total()
            return totalprice

            TODO()

        }

        /****************************************task 2********************************************/
        fun withManyArgs(arg1: Int, arg2: Double, arg3: Int = 0, arg4: String): String {
            return "boo"
        }

        //TODO
        //Finish the declaration of the argTakingLambda method to take a function of the
        // same type as withManyArgs
        //Note
        fun argTakingLambda(fn: (Int, Double, Int, String) -> String) : String{
            return fn(10, 22.2, 3, "handle")
        }

        //TODO
        fun task2(): String {
            //Make this code compile
            val result = argTakingLambda(::withManyArgs)
            return result

//            TODO()
        }

        /****************************************task 3********************************************/


        fun doSomeWork(func: (String, String) -> String): String {
            val funcResult = func("abc", "def")

            return funcResult
        }

        //TODO. Call doSomeWork with a lambda and return the result.
        // Look at testTask3 to see what the lambda should do.
        fun task3(): String {
            val result = doSomeWork { str1, str2 ->
                str2 + str1
            }
            return result
        }

    }
}

class TestFunctionalTasksSol {
    @Test
    fun testTask1() {
        val total = ExtensionTasksSol.task1()
        assertEquals(225.0, total)
    }


    @Test
    fun testTask2() {
        val result = ExtensionTasksSol.task2()
        assertEquals("boo", result)
    }

    @Test
    fun testTask3() {
        val result = ExtensionTasksSol.task3()
        assertEquals("defabc", result)
    }
}
