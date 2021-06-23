package ttl.introkot.larku.dao.file.fancy.composition

import ttl.introkot.larku.dao.BaseDAO
import ttl.introkot.larku.domain.Student

/**
 * @author whynot
 */
open class StrategicStatefulStudentDAO(val strategy: PersistenceStrategy<Student>) : BaseDAO<Student> {

    private var state: State<Student> = strategy.reify()

    open override fun insert(student: Student): Student {
        val nid = state.nextId.getAndIncrement()
        val studentWithId = student.copy(id = nid)
        state.items[nid] = studentWithId

        return studentWithId;
    }

    open override fun update(id: Long, student: Student): Boolean {
        return state.items.computeIfPresent(id) { id, oldStudent -> student} != null
    }


    open override fun delete(id: Long): Boolean {
        return state.items.remove(id) != null
    }

    open override fun find(id: Long) = state.items[id];

    open override fun count() = state.items.size.toLong()

    open override fun clear() {
        state.items.clear()
        state.nextId.set(1)
    }

    open override fun findAll() : List<Student> {
        return state.items.values.toList()
    }

    open override fun close() {
        strategy.serialize(state);
    }
}
