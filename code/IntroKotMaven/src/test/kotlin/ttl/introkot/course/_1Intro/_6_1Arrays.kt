package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */
//Difference between Arrays and Collections is that Arrays have to
//have an initial size and won't automatically resize.
//Also, all arrays are *Mutable*

class _6_1Arrays {
    /**
     * Simple Arrays.
     */
    @Test
    fun simpleArrays() {
        val arr1 = arrayOf(10, 20, 30)

        //Elements can be accessed by using .get method
        var first = arr1.get(0)
        //Elements can be accessed using overloaded [] operator
        var firstAlso = arr1[0]

        //Same for Setting
        arr1.set(0, 50)
        arr1[0] = 50
    }

    /**
     * Unlike Collections, Arrays do not get resized automatically.
     * So IndexOutOfBounds Exceptions are more possible.
     */
    @Test
    fun arrayIndexOutOfBounds() {
        //On get, same as for Lists
        val arr1 = arrayOf(10, 20, 30)

        //Elements can be accessed by using .get method
//        var first = arr1.get(4)

        //Or On set
        arr1[4] = 55
    }

    /**
     * Create an array of a particular type and size.
     * Second function gets the index as an argument and
     * is called once for each element to initialize it.
    */
    @Test
    fun arrayInitialization() {
        val arr1 = Array<Int>(10) {index -> index * 2}
        //Iterate with indices
        for(i in arr1.indices) {
            println("arr1[$i] = ${arr1[i]}")
        }

        //Iterate with index and value
        for((index, elem) in arr1.withIndex()) {
            println("arr1[$index] = $elem")
        }

        //Iterate with elements directly
        for(elem in arr1) {
            println("elem = $elem")
        }
    }

    /**
     * Factory methods available to create and initialize
     * primitive arrays
     */
    @Test
    fun primitiveArrayFactories() {
        //Will be an array of size 0
        val intArray = intArrayOf()
        //AIOBException
//        intArray[0] = 10;

        //Don't need to supply initialization function when
        //you use the Primitive Array Constructors.
        val intArray2 = IntArray(10)
        intArray2[0] = 10;
        println("intArray2.size: ${intArray2.size}")


        val charArray = CharArray(10)
        charArray[0] = 'x'

        val doubleArray = DoubleArray(10)
        doubleArray[0] = 11.2
    }
}
