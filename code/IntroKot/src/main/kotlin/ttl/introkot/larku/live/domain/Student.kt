package ttl.introkot.larku.live.domain

import java.time.LocalDate

/**
 * @author whynot
 */
data class Student(val id: Int, var name: String, val dob: LocalDate,
              val phoneNumbers: MutableList<String> = mutableListOf())

class BetterStudent(val id: Int, var name: String, val dob: LocalDate,
                   vararg phoneNumbers: String ) {

    val phoneNumbers = mutableListOf<String>(*phoneNumbers)

    constructor(id: Int, name: String, dob: LocalDate, phoneNumbers: MutableList<String> = mutableListOf())
        : this(id, name, dob, *phoneNumbers.toTypedArray())

//    init {
//        this.phoneNumbers.addAll(phoneNumbers)
//    }

    fun copy(id: Int = this.id, name: String = this.name, dob: LocalDate = this.dob,
               vararg phoneNumbers: String = this.phoneNumbers.toTypedArray()) : BetterStudent{
        val blah = BetterStudent(id, name, dob, *phoneNumbers)
        return blah
    }
}
