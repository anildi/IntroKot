package ttl.introkot.larku.service

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.OverrideMockKs
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import ttl.introkot.larku.dao.BaseDAO
import ttl.introkot.larku.domain.Email
import ttl.introkot.larku.domain.Student
import java.time.LocalDate

/**
 * @author whynot
 *
 * Unit test with MockK
 *
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceUnitTest {

    val daoMock = mockk<BaseDAO<Student>>()

    @OverrideMockKs
    val studentService: StudentService = StudentService(daoMock)

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Test
    fun createStudentTest() {
        val student = Student(5, "Joey", "383 838 393", LocalDate.now().minusYears(19), Email("a@b.com"))

        every {daoMock.insert(student)} returns student

        val newStudent = studentService.createStudent(student);

        verify{
            daoMock.insert(student)
        }
    }

    //Throw a "Too Young" Exception
    @Test
    fun createStudentTooYoungTest() {
        val student = Student(0, "Joey", "383 838 393", LocalDate.now().minusYears(15), Email("a@b.com"))

        every {daoMock.insert(student)} returns student
        assertThrows(RuntimeException::class.java, Executable {
            studentService.createStudent(student)

        })
        verify(exactly = 0){
            daoMock.insert(student)
        }
    }

    @Test
    fun updateStudentTest() {
        val oldStudent = Student(5, "Joey", "383 838 393", LocalDate.now().minusYears(19), Email("a@b.com"))

        oldStudent.name = "Firoza"

        every{daoMock.update(5, oldStudent)} returns true

        val bool = studentService.updateStudent(oldStudent)

        assertTrue(bool)

        verify(exactly = 1) {
            daoMock.update(5, oldStudent)
        }
    }

    @Test
    fun updateStudentTooYoungTest() {
        val oldStudent = Student(0, "Joey", "383 838 393", LocalDate.now().minusYears(15), Email("a@b.com"))

        oldStudent.name = "Firoza"

        every{daoMock.update(5, oldStudent)} returns true

        val bool = studentService.updateStudent(oldStudent)


        assertFalse(bool)

        verify(exactly = 0) {
            daoMock.update(5, oldStudent)
        }
    }


    @Test
    fun deleteStudentTest() {
        every{daoMock.delete(5)} returns true

        val bool = studentService.removeStudent(5);

        assertTrue(bool);
        verify(exactly = 1) {
            daoMock.delete(5)
        }
    }

    @Test
    fun deleteNonExistingStudentTest() {
        every{daoMock.delete(1000)} returns false
        val bool = studentService.removeStudent(1000)

        assertFalse(bool);

        verify(exactly = 1) {
            daoMock.delete(1000)
        }
    }
}