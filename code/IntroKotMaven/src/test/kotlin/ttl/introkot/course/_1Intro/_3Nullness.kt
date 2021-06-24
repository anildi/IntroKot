package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author whynot
 */

//won't compile
//var nullInitialized: String = ""
//won't compile
//var nullInitialized: String = null

var nullInitialized: String? = null

var string5: String = "abc"

fun useVar(input: String?) {
   //val len = input.length
//    if (input != null) {
//        //Smart cast
//        println("${input.length}")
//    }

    val i: Int = 10

    val len2: Int? = input?.length

    println("i: $i, len2: $len2")

    val len3: Int = input?.length ?: throw RuntimeException("Length can't be null")

    val len4: Int = input?.length ?: -1

    val len5: Int = possiblyNull().substring(2, 6).length
//
//
//    println("len: ${input.length}")
}

fun possiblyNull() : String {
    val str = null
   return str ?: ""
//    return null;
}

class TestNullNess {
    @Test
    fun main() {
//        useVar("abc")
        useVar(nullInitialized)
    }
}