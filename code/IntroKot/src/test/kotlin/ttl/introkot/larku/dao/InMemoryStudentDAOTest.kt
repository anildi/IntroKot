package ttl.introkot.larku.dao;

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import ttl.introkot.larku.dao.inmemory.InMemoryStudentDAO
import ttl.introkot.larku.domain.Email
import ttl.introkot.larku.domain.Student
import java.time.LocalDate

/**
 * @author whynot
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InMemoryStudentDAOTest
{
    lateinit var studentDAO: BaseDAO<Student>


    @BeforeEach
    fun init() {
        studentDAO = InMemoryStudentDAO()
    }

    @Test
    fun testCreateStudentAllGood() {
        val newStudent = Student(0, "Joe Zawinal", "383 939 9393", LocalDate.of(1960, 8, 10), Email("abc@d.com"))

        val savedStudent = studentDAO.insert(newStudent)

        assertEquals(1, savedStudent.id)

        println("savedStudent: $savedStudent");
    }

    @Test
    fun testCreateStudentBadEmail() {
        assertThrows(RuntimeException::class.java, Executable{
            val newStudent = Student(0, "Joe Zawinal", "383 939 9393", LocalDate.of(1960, 8, 10), Email("@d.com"))

            val savedStudent = studentDAO.insert(newStudent)

            assertEquals(1, savedStudent.id)

            println("savedStudent: $savedStudent")
        })
    }

    @Test
    fun testFindStudentGoodId() {
        val newStudent = Student(0, "Joe Zawinal", "383 939 9393", LocalDate.of(1960, 8, 10), Email("abc@d.com"))

        studentDAO.insert(newStudent);

        val foundStudent = studentDAO.find(1L)
        assertTrue(foundStudent != null)
    }

    @Test
    fun testFindStudentBadId() {
        val foundStudent = studentDAO.find(1L)
        assertTrue(foundStudent == null)
    }

    @Test
    fun testUpdateStudent() {
       testCreateStudentAllGood()
       val foundStudent =  studentDAO.find(1L) ?: fail("Student with id 1 Not found")

        val newPhoneNumber = "959 820 78484"
        foundStudent.phoneNumber = newPhoneNumber

        val foundStudent2 =  studentDAO.find(1L) ?: fail("Student with id 1 Not found")
       assertEquals(newPhoneNumber, foundStudent2.phoneNumber)

    }
}