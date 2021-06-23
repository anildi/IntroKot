package ttl.introkot.larku.dao.fancy.composition

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import ttl.introkot.larku.dao.file.fancy.composition.*
import ttl.introkot.larku.domain.Course
import ttl.introkot.larku.domain.Email
import ttl.introkot.larku.domain.Student
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate

/**
 * @author whynot
 */
class AlaCarteStrategyTest {

    @Test
    fun testJsonStrategyDAO() {
        val fileName = "/tmp/strategyStudents2.json"
        Files.deleteIfExists(Paths.get(fileName))

        val strategy = JsonStrategy(fileName)
        val dao = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()
        assertEquals(oldCount, count - 1)

        dao.close();

        val dao2 = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        assertEquals(count, dao.count())
    }

    @Test
    fun testJsonStrategyDAOUpdate() {
        val fileName = "/tmp/strategyStudents2.json"
        Files.deleteIfExists(Paths.get(fileName))

        val strategy = JsonStrategy(fileName)
        val dao = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()
        assertEquals(oldCount, count - 1)

        dao.close();

        val dao2 = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        assertEquals(count, dao2.count())

        val st = dao2.find(1) ?: fail("Student with id 1 Must exist")

        val newPhoneNumber = "686 94 94949 94"
        st.phoneNumber = newPhoneNumber

        dao2.update(1, st)
        dao2.close()

        val dao3 = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        assertEquals(count, dao3.count())

        val st2 = dao3.find(1) ?: fail("Student with id 1 Must exist")
        assertEquals(st2.phoneNumber, newPhoneNumber)
    }

    @Test
    fun testJsonStrategyDAODelete() {
        val fileName = "/tmp/strategyStudents2.json"
        Files.deleteIfExists(Paths.get(fileName))

        val strategy = JsonStrategy(fileName)
        val dao = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()
        assertEquals(oldCount, count - 1)

        dao.close();

        val dao2 = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        assertEquals(count, dao2.count())

        val st = dao2.find(1) ?: fail("Student with id 1 Must exist")

        dao2.delete(1)
        dao2.close()

        val dao3 = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        assertEquals(count - 1, dao3.count())

        val st2 = dao3.find(1)
        assertNull(st2)
    }

    @Test
    fun testSerialStrategyDAO() {
        val fileName = "/tmp/strategyStudents2.ser"
        Files.deleteIfExists(Paths.get(fileName))
        val strategy = SerialStrategy(fileName)

        val dao = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()
        assertEquals(oldCount, count - 1)
        dao.close();

        val dao2 = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        assertEquals(count, dao.count())
    }

    @Test
    fun testSerialPrint() {
        val strategy = SerialStrategy("/tmp/strategyStudents2.ser")

        var students = getAll(strategy)
        students.forEach(::println)


        println("Json")
        val js = JsonStrategy("/tmp/strategyStudents2.json")
        students = getAll(js)
        students.forEach(::println)


    }

    fun getAll(strategy: PersistenceStrategy<Student>) : List<Student>{
        val dao = ALaCarteStrategyStudentDAO(strategy::serialize, strategy::reify)
        val students = dao.findAll()
        return students;
    }

    @Test
    fun testOnTheFlyStrategy() {
        val serialize: (State<*>) -> Unit = {state ->
            println(state.toString())
        }

        val reify: () -> State<Student> = {State()}

        val dao = ALaCarteStrategyStudentDAO(serialize, reify)

        val oldCount = dao.count()

        dao.insert(Student(0, "Bird", "338 93283", LocalDate.now().minusYears(30), Email("a@b.com")))

        val count = dao.count()

        assertEquals(count, oldCount + 1)
        dao.close();

        val students = dao.findAll()
        students.forEach(::println)

        val dao2 = ALaCarteStrategyStudentDAO(serialize, reify)
        assertEquals(0, dao2.count())

    }

    @Test
    fun testCourseInMemory() {
        val course = Course(0L, "Intro to Math", "Math-101", LocalDate.now())
        val dao = ALaCarteStrategyCourseDAO()
        dao.insert(course)
        dao.insert(Course(0L, "Intro to Math", "Math-101", LocalDate.now()));

        val courses = dao.findAll()
        courses.forEach(::println)

        assertEquals(2, courses.count())
    }

    @Test
    fun testCourseToFile() {
        val course = Course(0L, "Intro to Math", "Math-101", LocalDate.now())
        val fileName =  "/tmp/geeedyap.json"
        val filePath =  Paths.get(fileName)
        Files.deleteIfExists(filePath)
        val reifier = {
            val path = filePath
            path.reifyStateJson<Course>(fileName)
        }

        val serialize = {state: State<Course> ->
            val path = filePath
            path.serializeStateJson(fileName, state)
        }

        val dao = ALaCarteStrategyCourseDAO(serialize = serialize, reify = reifier)
        dao.clear()
        dao.insert(course)
        dao.insert(Course(0L, "More Intro to Math", "Math-102", LocalDate.now()));

        val courses = dao.findAll()
        courses.forEach(::println)

        val count = dao.count()
        dao.close()
        assertEquals(2, courses.count())

        val dao2 = ALaCarteStrategyCourseDAO(serialize = serialize, reify = reifier)
        assertEquals(dao2.count(), count)
        val course2 = dao2.find(1) ?: fail("Couse2 cannot be null")
        assertNotNull(course2)
        assertTrue(course2.name.contains("Math"))
        dao2.close()
    }
}