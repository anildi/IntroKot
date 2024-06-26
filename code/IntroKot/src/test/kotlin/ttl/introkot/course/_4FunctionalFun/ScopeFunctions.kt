package ttl.introkot.course._4FunctionalFun

import org.junit.jupiter.api.Test
import ttl.larku.domain.Student
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

/**
 * @author whynot
 */

/**
 * Kotlin Scope functions are apply, run, let, also and with.  They
 * differ in their arguments and return types
 *
 * Advice from Kotlin docs: https://kotlinlang.org/docs/reference/scope-functions.html
 *
 * - Executing a lambda on non-null objects: let
 * - Introducing an expression as a variable in local scope: let
 * - Object configuration: apply
 * - Object configuration and computing the result: run
 * - Running statements where an expression is required: non-extension run
 * - Additional effects: also
 * - Grouping function calls on an object: with

 * The use cases of different functions overlap, so that you can choose the functions
 * based on the specific conventions used in your project or team.
 */

class ScopeFunctions {


    val jdbcURL = "jdbc:derby:/196.11.256.3/MyDb"
    fun task1() {
        val instance = Processor()

        /*******************************apply******************************************************/
//            public inline fun <T> T.apply(block: T.() -> Unit): T {
//                block()
//                return this
//            }
        val ap = Processor().apply {
            id = 10
            name = "Apply"
            headers.add("Someheader=SomeValue")
            headers.add("OtherHeader=OtherValue")
            address = "tcp://@xray"
        }

        println("ap: $ap")
        //Often used to initialize an object after construction
        //i.e. instead of this:
        val badStudent = Student("Furdly", Student.Status.HIBERNATING)
        badStudent.phoneNumber = "383 939 9393"
        badStudent.isFeePaid = true
        //You can write this:
        val student = Student("Furdly", Student.Status.HIBERNATING).apply {
            phoneNumber = "383 939 9393"
            isFeePaid = true
        }

        //Or, instead of this:
        val map = HashMap<String, Int>()
        map.put("One", 1)
        map.put("Two", 2)
        map.put("Three", 3)
        map.put("Four", 4)

        //You can write this:
        val map2 = HashMap<String, Int>().apply {
            put("One", 1)
            put("Two", 2)
            put("Three", 3)
            put("Four", 4)
        }


        /*******************************run******************************************************/

//            public inline fun <T, R> T.run(block: T.() -> R): R {
//                return block()
//            }

        val ap2 = Processor().run {
            id = 10
            name = "Run"
            this
        }


        //Not good for initializing because you have to return 'this;

        //Better if you don't care about the result, and/or you want to return
        //something else

        //**********************Example 1*********************************8
        //Call a bunch of functions on the receiver and then compute a
        //different return value.  In this case we want the final
        // processed value
        val ap1 = Processor(10)
        ap1.name = "Toaster"
        ap1.proxy = "stu.com"
        val xxa = ap1.process1()
        val xxb = ap1.process2()
        val xxc = ap1.process3()
        val processedValue1 = "${ap1.id} ${ap1.name}  $xxa  $xxb $xxc"

        //With run
        val processedValue2 = Processor(10).run {
            name = "Toaster"
            proxy = "stu.com"
            val xa = process1()
            val xb = process2()
            val xc = process3()
            "${id} ${name}  $xa  $xb $xc"
        }

        println("Run: x1: $processedValue1, x2: $processedValue2")

        /*******************************let******************************************************/


//            public inline fun <T, R> T.let(block: (T) -> R): R {
//                return block(this)
//            }
        //Not a good initializer
        val ap4 = Processor().let {
            it.id = 10
            it.name = "Let"
            it
        }

        //Takes lambda without receiver, that gets the called instance
        //as an argument (can be accessed with 'it', or named explicitly)
        //Good when you want to use the argument to do other work.
        //Also very useful for handling null checks, and transforming
        //an object into a different type.

        //*********Example 1********************************/

        val str4: String? = getPossiblyNullString()
        //val size = if(str4 != null) str4.length * str4.length else -1
        val sizeSq = str4?.let { it ->
            it.length * it.length
            //use non null String for other work
        } ?: -1  //Need this as an "else".

        str4?.let { it ->
            val sq = it.length * it.length
            //use sq for other work
        }

        //*********Example 2********************************/
        //Get an processor from somewhere
        val processor = getProcessor()

        //Without let
        if (processor != null) {
            val url = processor.address
            //Do something with the url
        }

        //With let
        getProcessor()?.let {
            val url = it.address
            //do something with the url
        }

        //Transformation.  Here we are going to get a Processor
        //and, if it is not null, use it to create a Connection object.
        val conneciton: Connection? = getProcessor()?.let {
            makeConnection(it)
        }

        /*******************************also******************************************************/

//            public inline fun <T> T.also(block: (T) -> Unit): T {
//                block(this)
//                return this
//            }

        val ap5: Processor = Processor().also {
            it.id = 10
            it.name = "Also"
        }

        //also is like apply, but gets the instance as an
        //argument.  Apply is more convenient for initialization
        //because you don't need to keep referencing 'it'

        //Used when you want to invoke an operation that will
        //use the receiver.  Or to create side effects, like
        //logging.
        val proc0 = getProcessor()
        if (proc0 != null) {
            makeConnection(proc0)
        }

        // versus
        val proc1 = getProcessor()?.also {
            makeConnection(it)
            println("made connection to ${it}")
        }


        println("Let: proc0: $proc0, proc1: $proc1")


        /*******************************with******************************************************/
//           public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
//                return receiver.block()
//             }

        //Again, not very good for initializing.
        val ap3: Processor = with(Processor()) {
            id = 10
            name = "With"
            this
        }

        //Good for using an object for other work
        //And you don't care about the result
        //"With this object, do something"
        with(Processor()) {
            val x = process2()   //this.process2()
            val y = process3()
            //do some other work with x and y
        }
        //With gets the instance as the first argument.
        println("with ap3: $ap3")
    }

    /**
     * A variation of the example above.  In this case, we want to start with a
     * Processor and create a connection from it and end up with the Connection object
     */
    fun task2() {
        val proc0 = getProcessor()
        val connection = if (proc0 != null) {
            val con = makeConnection(proc0)
            println("made connection1 to ${proc0}")
            con
        } else {
            null
        }

        //'let' can be used because it gives us the
        //context object as an argument and we can
        //return a different object.
        //And we are using 'also' for the side effect
        //of printing (logging).  'also' will return the
        //thing it is called on, which is our connection
        //object.
        val connection1 = getProcessor()?.let { proc ->
            makeConnection(proc).also {
                println("made connection1 to ${it}")
            }
        }


        //run can be used because we can change
        //the return type, as with 'let'
        val connection2 = getProcessor()?.run {
            makeConnection(this).also {
                println("made connection2 to ${it}")
            }
        }

        //with is awkward with nulls
        val connection3 = with(getProcessor()) {
            this?.run {
                val con = makeConnection(this)
                println("made connection3 to ${con}")
                con
            }
        }

        //Can't use 'also', since it returns the receiver
        //so we can't use it to transform
//        val connection0: Connection = getProcessor()?.also {
//            makeConnection(it).also {
//                println("made  to ${it}")
//            }
//        }

        //Similarly, 'apply' is not possible here
        //because it returns the receiver, so we can not
        //use it to transform the Processor into a Connection
//        val connection4: Connection = getProcessor()?.apply {
//            makeConnection(this).also {
//                println("made connection2 to ${this}")
//            }
//        }


        println("con1: $connection1, con2: $connection2, con3: $connection3")
    }

    val nextId = AtomicInteger(1)

    fun getProcessor(): Processor? =
        if(Random.nextDouble() > .2) {
            val request = Processor(nextId.getAndIncrement())
            request.name = "Default Processor"
            request.address = "http://xyz.com/getit"
            request
        } else {
            null
        }
    fun getUrl(): String? = if (Random.nextDouble() > .5) "jdbc:mysql://xyz.com/OrderDB" else null

    fun makeConnection(processor: Processor) = Connection(processor.address)

    fun getPossiblyNullString() = if(Random.nextDouble() < .5) null else "Boo"
}



data class Connection(val url: String) {}

class TestScopeFunctions {

    @Test
    fun testTask1() {
        val sf = ScopeFunctions().apply {
            task1()
        }
    }

    @Test
    fun testTask2() {
        val sf = ScopeFunctions().apply {
            task2()
        }
    }
}
