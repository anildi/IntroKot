package ttl.introkot.course._1Intro

/**
 * @author whynot
 */

//main method.  Note Array declaration
fun main(args: Array<String>){
//    println("Here we go with arg size of: ${args.size}")
    callDefaulter()
}

fun process(input: Int) {
    val list: List<Int> = listOf(10, 20, 30)
    val first: Int? = list.get(0)

    var result = input * 10

    println("result is $result")    //string interpolation
}

fun someWork(a: Int, b: Int) : Int {
    val result = a * b
    return result
}

//Default arguments
fun defaulter(name: String, id: Int = 22, age: Int = 10) {
   println("def: $name, $id $age")
}

fun callDefaulter() {
//    defaulter("abc")
//    defaulter("abc", 220)
    defaulter("abc",  age = 4000)
}

fun callWithNamedArguments() {
    defaulter("abc", age = 20);
}

//compile time constant
const val MAX = 1000

