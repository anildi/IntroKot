package ttl.introkot.course._2ClassesEtc

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */

class MyClass {
    private fun foo() {
        println("MyClass.foo: ${this::class.qualifiedName}")
    }

    //This is the closest you can get to "static" things
    //for a class in Kotlin.
    companion object {
        @JvmField //- Can be accessed as a field in Java, i.e. MyClass.label
        //@JvmStatic - Can be accessed as a get Method in Java, i.e. MyClass.getLabel()
        val label = "Important"

        @JvmStatic
        fun fakeStaticFun() {
            println("Companion: ${this::class.qualifiedName}")
            println("fakeStatic called")
        }

        //One use of Companion objects is to create builders
        fun buildAnInstance(): MyClass {
            val mc = MyClass()
            //do complicated initialization for mc
            //...
            return mc
        }
    }

    //This is a Singleton called 'A'.
    //You invoke its behaviours statically
    //through the name, e.g. MyClass.A.callFooFromA(..),
    //MyClass.A.i = 20
    object A {
        var i = 0
        fun callFooFromA(mc: MyClass) {
            println("A: ${this::class.qualifiedName}")
            mc.foo()
        }
    }
}

class TestObjects {
    @Test
    fun testObjects() {
        val mc = MyClass()

        MyClass.A.callFooFromA(mc)

        //Companion Object syntax
        MyClass.fakeStaticFun()

        val builtMyClass = MyClass.buildAnInstance()
    }
}
