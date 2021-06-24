package ttl.introkot.course._1Intro

/**
 * @author whynot
 */

//Type is inferred.
//Compiler chooses primitive or reference based on context
//val => Read Only, *not* necessarily immutable
//var => Read Write
val i: Int = 20

var changeAble = 10

fun foo() {
//   i = 20
    changeAble = 35
}

object RunVar {
    @JvmStatic
    fun main(args: Array<String>) {
        println("List class is ${list::class.java}")
        list.forEach(::println)
    }
}

//immutable list
val list: List<Int> = listOf(10, 20, 30)
//mutable
val mutList: MutableList<Int> = mutableListOf(10, 20, 30)

fun doLists() {
    //list = listOf(30, 30)
//    list.add(50)
    mutList.add(50)

    val mList: MutableList<Int> = mutableListOf(10, 20, 30)
    mList.add(20)

    takeAList(mList)
}

fun takeAList(list: List<Int>) {
//    list.add(50)
}

