package ttl.introkot.course._5CollectionOps

/**
 * @author whynot
 */

data class Address(val street: String, val zipCode: String)

data class Person(val id: Int, val name: String, val address: Address, val phoneNumbers: MutableList<String>,
        val points: Int = 100)

val persons = listOf(
        Person(1, "Peeka", Address("11 Main Street", "19191"), mutableListOf(), 10),
        Person(2, "Sammy", Address("12 Fahrenheit", "19191"), mutableListOf("383 008 4840", "689 904 9494")),
        Person(3, "Firhan", Address("22 South Ave", "19191"), mutableListOf("895 238 38839"), 1000),
        Person(4, "Jong Li", Address("2 Ring Road", "34354"), mutableListOf("280 595 95050"), 2000),
        Person(5, "Sarla", Address("Outside here", "74364"), mutableListOf("895 238 38839")),
        Person(5, "Sally", Address("Inside here", "74364"), mutableListOf("895 238 38839", "389 040 05050", "488 9494 949")),
        Person(5, "Julian", Address("Upside here", "74364"), mutableListOf("895 238 38839", "987 654 332123")),
        Person(6, "Raster", Address("111 Upside place", "68431"), mutableListOf(), 20),
        Person(7, "Phulan", Address("18 Rabbit Hole", "38383"), mutableListOf("+92 83 99 9393 933"), 50)
)

