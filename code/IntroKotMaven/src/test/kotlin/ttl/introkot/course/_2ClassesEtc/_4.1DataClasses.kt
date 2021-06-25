package ttl.introkot.course._2ClassesEtc

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */

class Person(val name: String, val id: Int) {
    override fun toString(): String {
        return "Person(name='$name', id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (name != other.name) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + id
        return result
    }

    operator fun component1() = name
    operator fun component2() : Int{
        return id
    }
}

//Data classes get lot's of goodies thrown in by the compiler
//e.g. toString, equals and hashCode, copy, and componentN functions.
//A componentN function is created for each property in the primary constructor.
//This class will get component1() and component2()
data class PersonToo(val name: String, val id: Int)

class DataClassTest {
    @Test
    fun testDataClass() {
        val p1 = Person("Tanaka", 20)
        val p2 = Person("Tanaka", 20)
        println("p1: $p1, equal: ${p1 == p2}")
        println("p1: $p1, equal references: ${p1 === p2}")

        val(n3, i3) = p1

        val p3 = PersonToo("Tanaka", 20)
        val p4 = PersonToo("Tanaka", 20)
        println("p3: $p3, equal: ${p3 == p4}")


        println("p3: $p3, reference equal: ${p3 === p4}")
//
//        //Destructuring. Possible because of the componentN functions.
//        //The destructuring call get's converted into a call to Pair(p3.component1(), p3.component2())
        //val(name, id) = p3.component1() to p3.component2()
        //val(name, id) = Pair(p3.component1(), p3.component2())
        val n = p3.name
        val i = p3.id

        val (name, id) = p3
        println("$name, $id")
//
//        //Copy an object, changing only some properties
        val p5 = p4.copy(name = "Prill")
        println("p5: $p5, equal: ${p3 == p5}")
    }
}