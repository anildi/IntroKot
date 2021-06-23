package ttl.introkot.larku.dao.fancy.composition

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import ttl.introkot.larku.dao.file.fancy.composition.JsonStrategy
import ttl.introkot.larku.dao.file.fancy.composition.PersistenceStrategy
import ttl.introkot.larku.dao.file.fancy.composition.SerialStrategy
import ttl.introkot.larku.dao.file.fancy.composition.StrategicStatefulStudentDAO
import ttl.introkot.larku.domain.Email
import ttl.introkot.larku.domain.Student
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

/**
 * @author whynot
 */

class StrategicDAOTest {

    @Test
    fun testJsonStrategyDAO() {
        val fileName = "/tmp/strategyStudents.json"
        Files.deleteIfExists(Paths.get(fileName))

        val strategy = JsonStrategy(fileName)

        val dao = StrategicStatefulStudentDAO(strategy)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()
        dao.close();

        assertEquals(oldCount, count - 1)

        val dao2 = StrategicStatefulStudentDAO(strategy)
        val x = dao2.find(1)
        println("x: $x")
        assertEquals(count, dao2.count())
//
//        val st = dao2.findAll().firstOrNull{it.name.contains("Bird")}
//        assertNotNull(st)

    }

    @Test
    fun testSerialStrategyDAO() {
        val fileName = "/tmp/strategyStudents.ser"
        Files.deleteIfExists(Paths.get(fileName))

        val strategy = SerialStrategy(fileName)
        val dao = StrategicStatefulStudentDAO(strategy)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()
        dao.close();

        assertEquals(oldCount, count - 1)

        val dao2 = StrategicStatefulStudentDAO(strategy)
        val x: Student = dao2.find(1) ?: fail("Student with id 1 can not be null")
        println("x: $x")
        assertEquals(count, dao2.count())

    }

    @Test
    fun testSerialPrint() {
        val strategy = SerialStrategy("/tmp/strategyStudents.ser")

        var students = getAll(strategy)
        students.forEach(::println)


        println("Json")
        val js = JsonStrategy("/tmp/strategyStudents.json")
        students = getAll(js)
        students.forEach(::println)


    }

    fun getAll(strategy: PersistenceStrategy<Student>) : List<Student>{
        val dao = StrategicStatefulStudentDAO(strategy)
        val students = dao.findAll()
        return students;
    }
}
