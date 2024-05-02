package ttl.introkot.course._1Intro

/**
 *
 * @author whynot
 */

//Function declarations outside a class.
//main method.  _Note Array declaration
fun main(args: Array<String>){
//    println("Here we go args") //No semi colons!!


//    defaulter("Boo")
//    defaulter("Boo", 14)
//    defaulter("Boo", 14, 2000)

    //defaulter("Boo", age = 2000)
    defaulter("Boo", age = 2000)

    val result = someWork(10, 10)
//    println("The result is: $result")
}

fun process(input: Int) {
    val result = input * 10
    println("result is $result")    //string interpolation
}

fun someWork(a: Int, b: Int) : Int {
    val result = a * b

    return result
}

//Default arguments
fun defaulter(name: String, id: Int = 22, age: Int = 10) {
   println("def: $name, id: $id  age: $age")
}

fun callDefaulter() {
    defaulter("abc", 220)
}

fun callWithNamedArguments() {
    defaulter("abc", age = 20);
}

//compile time constant
const val MAX = 1000

