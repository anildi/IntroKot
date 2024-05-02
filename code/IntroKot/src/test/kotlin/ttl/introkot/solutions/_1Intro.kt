package ttl.introkot.student.tasks.solutions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ttl.introkot.student.tasks.LTHolder
import ttl.introkot.student.tasks.WHolder
import ttl.introkot.student.tasks.initSkill

/**
 * @author whynot
 */


/****************************Task 1*************************************/

//Note - new function.
fun getAStringSol(input: Int): String {
    return "Boo"
}

//TODO
fun task1(): String {
    //Uncomment out the lines below and add
    //any necessary code to make them compile

    val result = getAStringSol(10)
    return result

    return TODO()
}

/****************************Task 2*************************************/

//niceness and alias can be any value and are not required
//Don't change the types of the parameters
fun someFunSol(prodName: String, niceness: Int = 0, price: Double, alias: String = ""): String {

    return prodName;
}

//TODO
fun task2(): String {
    //Make changes so the following code compiles.
    //Don't change the types of the arguments

    //Note - use parameter names to selective pass non default arguments.
    val result = someFunSol(prodName = "ShinyThing", price = 22.2)
    return result

    return TODO()
}

/****************************Task 3*************************************/

//Note - change the List to a MutableList
fun listAdderSol(input: MutableList<Int>) {
    val local = input
    //Make changes to make this code compile
    local.add(10)

    //TODO()
}

//TODO
//Make changes in 'listAdder' above to make the test run successfully
fun task3(): List<Int> {
    val list = mutableListOf<Int>()
    listAdderSol(list)
    return list
}


/****************************Task 4*************************************/
class LTHolderSol {
    //    var dbLoc: String = TODO() //Comment this out
    //TODO
    //Modify so this declaration compiles
    //Note - make it a lateinit, *and*, change the val to a var
    lateinit var dbLoc: String

    fun initDbLoc() {
        dbLoc = "xyz.com/db"
    }

    //TODO
    //Modify the dbLoc declaration above
    //testTask4 run
    fun task4(): String {
        initDbLoc()
        return dbLoc
    }
}

/****************************Task 5*************************************/
//Note - use Nullable String as the type.
var skillSol: String? = ""

//TODO
fun initSkillSol(newSkill: String?) {
    //Make this next line line work without
    //changing any other code in this function.
    skillSol = newSkill
}

//TODO
fun task5(): String? {
    initSkillSol("carpenter")
    return skillSol
}

/****************************Task 6*************************************/

class WHolderSol {
    enum class Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    //TODO
    //Note - when is most appropriate here.  We don't need an
    // else in the when because we have specified all elements of the enum.
    // Also note the direct return from the when
    fun findPriorityWithPoints(priority: Priority): Pair<Priority, Int> =
    //Write code to return a Pair based on Priority
    //HIGH => 10
    //MEDIUM => 5
            //LOW => 2
            when (priority) {
                Priority.HIGH -> priority to 10
                Priority.MEDIUM -> priority to 5
                Priority.LOW -> priority to 2
            }


    fun task6(): Pair<Priority, Int> {
        val result = findPriorityWithPoints(Priority.HIGH)
        return result
    }
}

/****************************Task 7*************************************/
//TODO - You have to write your own test for this one.
//TODO Write a function that will count all numbers between two
// limits (specified by arguments) that are divisible by either 3 and/or 7.
// Fill in testTask7 to test your function
fun counterSol(lower: Int, upper: Int) : Int {
    var result = 0
    for( i in lower..upper) {
//       if(i % 3 == 0 || i % 7 == 0) {
//          result++
//       }
       if(i % 3 == 0 ) {
          result++
       }
       if(i % 7 == 0) {
          result++
       }
    }
    return result
}

/****************************End Tasks*************************************/


/****************************Start Tests*************************************/

class TestIntroTasksSol {

    @Test
    fun testTask1() {
        val result = task1()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testTask2() {
        val result = task2()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testTask3() {
        val result = task3()
        assertEquals(1, result.size)
    }

    @Test
    fun testTask4() {
        val ltHolder = LTHolderSol()
        val result = ltHolder.task4()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun testTask5() {
        val result = task5()
        assertTrue(!result.isNullOrEmpty())
    }

    @Test
    fun testTask6() {
        val wh = WHolderSol()
        val result = wh.task6()
        assertTrue(result.first == WHolderSol.Priority.HIGH && result.second == 10)
    }

    //TODO - complete this test.  Implementation will depend
    // on how you have defined the corresponding function
    @Test
    fun testTast7() {
        val lower = -500
        val upper = 500
        val num = upper - lower + 1

        val result = counterSol(lower, upper)

        assertEquals(num / 3 + num / 7, result)
    }
}
