package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author whynot
 */

//won't compile
//var nullInitialized: String
//won't compile
//var nullInitialized: String = null

var nullInitialized: String? = null

fun useVar(input: String?) {
    if (input != null) {
        //Smart cast
        println("${input.length}")
    }

    input?.length
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

    val l2 = input?.let {
        input.length
    }

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
    fun main() {
        useVar(nullInitialized)
    }
}