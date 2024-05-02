package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */

class _6_1Collections {
//All the standard collections are available.
//An added twist in Kotlin is that you can have
//mutable and immutable references to collections

    fun listExamples() {
        //listOf gives you an immutable List
        val immutableList = listOf("2", "5", "15")
//    immutableList.add(20);   //Compile error

        //mutable Lists with mutableListOf
        val mutableList: MutableList<Int> = mutableListOf(10, 20, 30)
        mutableList.add(35)
    }

    @Test
    fun setExamples() {
        //A set is an *unordered* Collection that contains no duplicates.
        //Immutable
        val immutableSet = setOf(2, 3, 2, 5)
        immutableSet.forEach { num -> println("num: $num") }

//        immutableSet.add(25) //Compile error

        //Mutable Set
        val mutableSet = mutableSetOf(2, 3, 2, 5)
        mutableSet.add(25)
    }

    fun mapExamples() {
        //Immutable
        //Key Value pair
        val immutableMap = mapOf(Pair(10, "Ten"), Pair(20, "Twenty"), Pair(30, "Thirty"))
        //A different way to make Pairs
        val immutableMap2 = mapOf(10 to "Ten", 20 to "Twenty", 30 to "Thirty")
//        immutableMap.put(50, "Fifty")   //Compile Error

        //Get using .get
        val strTen = immutableMap.get(10)
        //Get using overridden [] operator
        val strTen2 = immutableMap[10];

        //Mutable
        val mutableMap = mutableMapOf(10 to "Ten", 20 to "Twenty", 30 to "Thirty")
        //add using 'put'
        mutableMap.put(50, "Fifty")
        //Or add using overridden [] operator
        mutableMap[50] = "Fifty"
    }

}
