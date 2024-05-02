package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.UnsupportedOperationException


class SimpleTestIfAndWhen {
    enum class Grade {
        A, B, C
    }

    fun aVarWithIf(score: Int): String {
        val result: String
        if (score > 90) {
            result = "Excellent"
        } else if (score > 80) {
            result = "Good"
        } else if (score > 70) {
            result = "Acceptable"
        } else {
            result = "Not Acceptable"
        }
        return result
    }

    @Test
    fun aVarWithIf() {
        var p = aVarWithIf(95)
        assertEquals("Excellent", p);

        p = aVarWithIf(85)
        assertEquals("Good", p);

        p = aVarWithIf(75)
        assertEquals("Acceptable", p);

        p = aVarWithIf(65)
        assertEquals("Not Acceptable", p);
    }

    fun simpleWhen(score: Int) : String{
        val result: String
        when(score) {
           in 90..100 ->  result = "Excellent"
            in 80..89 ->  result = "Good"
            in 70..79 ->  result = "Acceptable"
            else -> result = "Not Acceptable"
        }

        return result
    }

    @Test
    fun testSimpleWhen() {
        var p = simpleWhen(95)
        assertEquals("Excellent", p);

        p = simpleWhen(85)
        assertEquals("Good", p);

        p = simpleWhen(75)
        assertEquals("Acceptable", p);

        p = simpleWhen(65)
        assertEquals("Not Acceptable", p);
    }

    //If as an Expression with an enum, requires an else
    fun aVarWithIf(grade: TestIfAndWhen.Grade): String {
        val result = if (grade == TestIfAndWhen.Grade.A) {
            "Excellent"
        } else if (grade == TestIfAndWhen.Grade.B) {
            "Good"
        } else if (grade == TestIfAndWhen.Grade.C) {
            "Acceptable"
        } else {
            throw UnsupportedOperationException("Unknown Grade: $grade")
        }
        return result
    }

    //No 'else' required if you use a 'when' as an expression
    fun aVarWithWhenWithEnum(grade: Grade): String {
        val result = when (grade) {
            Grade.A -> "Excellent"
            Grade.B -> "Good"
            Grade.C -> "Acceptable"
        }
        return result
    }

    //More cleanup.  Use the return of the
//when expression directly as the value
//of the function
    fun aVarWithWhenCleaned(grade: Grade) = when (grade) {
        Grade.A -> "Excellent"
        Grade.B -> "Good"
        Grade.C -> "Acceptable"
    }
}
