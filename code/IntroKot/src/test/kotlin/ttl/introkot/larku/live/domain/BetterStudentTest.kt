package ttl.introkot.larku.live.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * @author whynot
 */
class BetterStudentTest {

    @Test
    fun testStudentPrimaryConstructor() {
        val s1 = BetterStudent(10, "Parler", LocalDate.of(1965, 5, 6))
        assertEquals(0, s1.phoneNumbers.size)

        val s2 = s1.copy(phoneNumbers = arrayOf("383 838 92929", "006 8075 992 29"))
        assertEquals(2, s2.phoneNumbers.size)

        assertFalse(s1.phoneNumbers === s2.phoneNumbers)
    }

    @Test
    fun testStudentVarArgs() {
        val s1 = BetterStudent(10, "Parler", LocalDate.of(1965, 5, 6), "9945 933 92292")
        assertEquals(1, s1.phoneNumbers.size)

        val s2 = s1.copy()
        assertEquals(1, s2.phoneNumbers.size)

        assertFalse(s1.phoneNumbers === s2.phoneNumbers)
        assertTrue(s1.phoneNumbers == s2.phoneNumbers)
    }

    @Test
    fun testStudentWithList() {
        val s1 = BetterStudent(10, "Parler", LocalDate.of(1965, 5, 6), mutableListOf("9945 933 92292"))
        assertEquals(1, s1.phoneNumbers.size)

        val s2 = s1.copy()
        assertEquals(1, s2.phoneNumbers.size)

        assertFalse(s1.phoneNumbers === s2.phoneNumbers)
        assertTrue(s1.phoneNumbers == s2.phoneNumbers)
    }
}