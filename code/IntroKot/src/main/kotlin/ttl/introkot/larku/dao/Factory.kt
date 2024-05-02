package ttl.introkot.larku.dao

import ttl.introkot.larku.dao.inmemory.InMemoryStudentDAO
import ttl.introkot.larku.domain.Student
import ttl.introkot.larku.service.StudentService

/**
 * @author whynot
 */

class DAOFactory {
    companion object {


        @JvmStatic
        fun studentDAO(): BaseDAO<Student> {
            return InMemoryStudentDAO()
        }

        @JvmStatic
        fun studentService(): StudentService {
            return StudentService(studentDAO());
        }
    }
}


