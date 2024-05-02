package ttl.introkot.course.playground

/**
 * @author whynot
 */
class Meeting {
    var canClose = false

    fun close() : Boolean{
        return true
    }
}

fun closeMeeting(m: Meeting?) : Boolean?{
    return m?.let {
        if(m.canClose)
            m.close()
        else
            false
    }
}

fun closeMeeting2(m: Meeting?) : Boolean?{
    return if(m == null) {
        null
    }else {
        if(m.canClose)
            m.close()
        else
            false
    }
}
