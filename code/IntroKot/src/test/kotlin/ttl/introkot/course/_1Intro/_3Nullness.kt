package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author whynot
 */

//val someString = "";
//won't compile
//var nullInitialized: String = ""
//won't compile
//var nullInitialized: String = null

var nullInitialized: String? = "abc" //null

var notNullString: String = "abc"

fun useVar(input: String?) {
//    val length = nullInitialized.length
//
//    if (input != null) {
//        //Smart cast
//        println("${input.length}")
//    } else {
//        println("null")
//    }
//
    println(input?.length)
}

//Can use the null check operator and/or
//the "elvis" operator to help with null checks
fun checkNulls(input: String?) {
    //Case 1
//    val l1 = if(input != null) {
//        input.length
//    }else {
//        null
//    }

    //Case 2
//    val l3 = if(input != null) {
//        input.length
//    } else {
//        -1
//    }

    val l4 = input?.length ?: -1

    //Case 3
//    if(input != null) {
//        input.length
//    } else {
//        val result = 42  //after some extended calculation
//        42
//    }

    val l6 = input?.length ?: run {
        val result = 42 //after some extended calculateion
        42
    }
}

class TestNullNess {
    @Test
    fun testNullInitialized() {
        //useVar(nullInitialized)
//        useVar("abc")
        checkNulls("abc")
    }
}