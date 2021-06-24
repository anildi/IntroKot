package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */


//Vals *have* to be initialized when constructed
//val ii: Int = 10

//With vars you have an out with lateinit.

//This won't compile
var notInitialized : String? = null

lateinit var initialized: String

fun lateInitVarBoom() {
    //Boom
    println("before anything: $initialized")
    val x = initialized
    println("${initialized.length}")
}

fun lateInitVar() {
    initialized = "Phew"
    //No Boom
    val x = initialized
    println("${initialized.length}")
}

class LateInitTest {
    @Test
    fun main() {
        lateInitVarBoom()
//        lateInitVar()
    }
}
