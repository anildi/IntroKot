package ttl.introkot.course._4FunctionalFun

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */
class Person(val firstName: String, val lastName: String) {
//    fun fullName() : String = "$firstName $lastName"
}

fun Person.fullName() : String {
    return "$firstName $lastName"
}

fun Person.fullNameAlso() : String = "$firstName $lastName"

fun String.randomize() : String {
    //Get the char array for the String
    val array2 = this.toCharArray()
    //Shuffle that
    array2.shuffle()
    //And back to a new String
    val ns = String(array2)

    return ns

}


class BasicExtensionTest {
    @Test
    fun basicTest() {
        val p = Person("Joe", "Smith")
        println("first and last: ${p.firstName} ${p.lastName}")

        println("fullName: '${p.fullName()}'")

        val s = p.fullName().randomize()

        println("randomized: '$s'")
    }
}
