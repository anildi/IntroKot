package ttl.introkot.larku.dao.file.fancy.composition

import com.fasterxml.jackson.databind.JavaType
import ttl.introkot.larku.dao.jMapper
import ttl.introkot.larku.domain.Student
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap
import javax.swing.UIManager.put

/**
 * @author whynot
 */

inline fun <reified T> jsonSerializer(fileName: String) : (state: State<T>) -> Unit {
    val result: (State<T>) -> Unit  = { state ->
        val path = Paths.get(fileName)
        path.serializeStateJson(fileName, state)
    }

    return result;
}

inline fun <reified T> jsonReifier(fileName: String) : () -> State<T> {
    val result: () -> State<T>  = {
        val path = Paths.get(fileName)
        path.reifyStateJson(fileName)
    }

    return result;
}

/**
 * The file is the one connected to the Path
 * on which this function has been called, i.e. 'this'
 *
 * We need a reified Generic because we want to have jMapper
 * gives us back a State<T>, with T being whatever the function is
 * currently being called with.  So far I can only get it to work and correctly
 * "reify" by using the constructParametricType method.
 * Reified is needed to be able to say T::class.java.
 * The TypeReference<State<T>> does not seem to work.
 *
 * Also, the generic declaration in jMapper.readValue<State<T>>(...) seems necessary.
 * Doesn't compile without the <State<T>>.
 *
 * @receiver Path object pointing to file
 * @return Either the old State if it exists, or a new State
 */
inline fun <reified T> Path.reifyStateJson(inputFileName: String) : State<T> {
    return if (Files.exists(this)) {
        val p = Paths.get(inputFileName)
        val fileReader = FileReader(inputFileName)
//            val typeRef = object : TypeReference<State<T>>() {}
        val type: JavaType = jMapper.typeFactory.constructParametricType(State::class.java, T::class.java)

        val state = jMapper.readValue<State<T>>(fileReader, type)

        state
    } else {
        State()
    }
}

/**
 * Write the give State out the the file that is connected
 * to the Path on which this method has been called
 *
 * @receiver Path object pointing to the file
 * @param state The State to be serialized
 */
fun <T> Path.serializeStateJson(inputFileName: String, state: State<T>) {
    val mapper = jMapper
//    val json = mapper.writeValueAsString(state)

    val fileWriter = FileWriter(this.toString())
    fileWriter.use {
        val json = mapper.writeValue(it, state)
    }
}


/**
 * Allow us to initialize a
 */
fun <K, V> ConcurrentHashMap<K, V>.putPairs(vararg pairs: Pair<K, V>) {
    for(p in pairs) {
        put(p.first, p.second)
    }
}
