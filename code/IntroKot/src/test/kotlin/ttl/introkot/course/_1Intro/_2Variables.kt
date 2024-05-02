package ttl.introkot.course._1Intro

/**
 * @author whynot
 */

//Type is inferred.
//Compiler chooses primitive or reference based on context
//val => Read Only, *not* necessarily immutable  //value
//var => Read Write
val i = 10


var changeAble = 10   //'variable'

fun foo(radius: Int) {

    val fromUser = getFromUser();
    var area = Math.PI * radius * radius

//   i = 20
    changeAble = 35
}

fun getFromUser() : Int {
    var x = 10
    return x
}

//immutable list
val list: List<Int> = listOf(10, 20, 30)

//mutable
val mutList: MutableList<Int> = mutableListOf(10, 20, 30)

fun doLists() {
//    list.add(50)

    mutList.add(50)

    takeAList(mutList)
}

fun takeAList(list: List<Int>) {

//    list.add(50)
}

