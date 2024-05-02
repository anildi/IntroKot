package ttl.introkot.slideexamples

/**
 * @author whynot
 */
class Functions {

    val funPtr: (String) -> Int = { arg -> arg.length * 2}

    //If only one argument then you can refer to it with 'it'
    val funPtrWithIt: (String) -> Int = { it.length * 2}

    val doubleLength: (String) -> Int = { arg -> arg.length * 2 }
    val doubleLength2 = { arg: String -> arg.length * 2 }

    fun checkInput(message: String, predicate: (String) -> Boolean) : Boolean{
        return predicate(message);
    }


}
