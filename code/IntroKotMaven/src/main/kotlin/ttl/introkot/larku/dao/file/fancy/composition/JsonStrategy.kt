package ttl.introkot.larku.dao.file.fancy.composition

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.module.kotlin.readValue
import ttl.introkot.larku.dao.jMapper
import ttl.introkot.larku.domain.Student
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author whynot
 */
class JsonStrategy(val inputFileName: String) : PersistenceStrategy<Student> {

    override fun serialize(state: State<Student>) {
        val filePath = Paths.get(inputFileName)
        filePath.serializeState(state)
    }

    override fun reify() : State<Student> {
        val filePath = Paths.get(inputFileName)
        val state = filePath.reifyState<Student>()
        return state
    }


    /**
     * The file is the one connected to the Path
     * on which this function has been called, i.e. 'this'
     *
     * If the file exists, then open and read it.
     *
     * We need a reified Generic because we want to have jMapper
     * gives us back a State<T>, with T being whatever the function is
     * currently being called with.  So far I can only get it to work and correctly
     * "reify" by using the constructParametricType method.
     * Reified is needed to be able to say T::class.java.
     *
     * The TypeReference<State<T>> does not seem to work.
     *
     * Also, you seem to need the generic declaration in jMapper.readValue<State<T>>(...)
     * Doesn't compile without the <State<T>>.  Not sure why not
     *
     * @receiver Path object pointing to the json file.
     * @return Either the old State if it exists, or a new State.
     */
    private inline fun <reified T> Path.reifyState(): State<T> {
        if (Files.exists(this)) {
            val p = Paths.get(inputFileName)
            val fileReader = FileReader(inputFileName)
//            val typeRef = object : TypeReference<State<T>>() {}
            val type: JavaType = jMapper.typeFactory.constructParametricType(State::class.java, T::class.java)
//            val state = jMapper.readValue(fileReader, State::class.java)
            val state = jMapper.readValue<State<T>>(fileReader, type)

            return state
        } else {
            return State()
        }
    }

    /**
     * Write the give State out the the file that is connected
     * to the Path on which this method has been called
     *
     * @receiver Path object pointing to the file
     * @param state The State to be serialized
     */
    private fun <T> Path.serializeState(state: State<T>) {
        val mapper = jMapper
        val json = mapper.writeValueAsString(state)

        val fileWriter = FileWriter(inputFileName)
        fileWriter.use {
            val json = mapper.writeValue(it, state)
        }
    }
}
