package ttl.introkot.slideexamples

import org.junit.jupiter.api.Test

/**
 * @author whynot
 */
class Collections {

    @Test
    fun plusMinusOperators() {
        val list = mutableListOf<Int>()
        list += 10
        list += 20

        val set = mutableSetOf(10, 20, 30)
        set -= 10

        println("list: $list, set: $set")
    }

    @Test
    fun zipDemo() {
        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")



        val result = (colors.zip(animals) { color, animal ->
            "The ${animal.replaceFirstChar { it.toUpperCase() }} is $color"
//            println(colors.zip(animals) { color, animal -> "The ${animal.replaceFirstChar { it.uppercase() }} is $color"})
        })

        println(result)
    }

    fun String.replaceFirstChar(replacer: (Char) -> Char) : String{
        val replacedChar = replacer(this[0])
        val newString = replacedChar + this.substring(1)
        return newString
    }
}