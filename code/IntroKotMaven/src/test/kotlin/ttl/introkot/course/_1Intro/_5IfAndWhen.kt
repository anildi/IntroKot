package ttl.introkot.course._1Intro

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.UnsupportedOperationException
import java.time.LocalTime


class TestIfAndWhen {

    val xyz: Int = 10

    fun processScoresIf(score: Double): Pair<String, Grade> {
        val description: String
        val grade: Grade

        if (score >= 90.0) {
            description = "Excellent"
            grade = Grade.A
        } else if (score in 70.0..89.9) {
            description = "Good"
            grade = Grade.B
        } else {
            description = "Acceptable"
            grade = Grade.C
        }
        //Use description and grade
        //Notice that the use of the else allows you
        //to use the variables here.  If you remove it,
        //you will get a compile error.
        return Pair(description, grade)
    }

    @Test
    fun testProcessScoresIf() {
        var p = processScoresIf(91.0);
        assertEquals(Grade.A, p.second);

        p = processScoresIf(89.9)
        assertEquals(Grade.B, p.second);

        p = processScoresIf(85.0)
        assertEquals(Grade.B, p.second);

        p = processScoresIf(60.0)
        assertEquals(Grade.C, p.second);
    }

    fun processScores(score: Double): Pair<String, Grade> {
        val description: String
        val grade: Grade
        when {
            score >= 90.0 -> {
                description = "Excellent"
                grade = Grade.A
            }
            score in 70.0..89.9 -> {
                description = "Good"
                grade = Grade.B
            }
//        score < 70.0 -> {
            else -> {
                description = "Acceptable"
                grade = Grade.C
            }
        }
        //Use description and grade
        //If you don't have an 'else' part to the
        //'when' above, you will get an uninitialized
        //error on the variables if you try to use them.
        //To make this compile, change the
        //last "case" into an else.
        return Pair(description, grade)
    }

    @Test
    fun testProcessScores() {
        var p = processScores(91.0);
        assertEquals(Grade.A, p.second);

        p = processScores(89.9)
        assertEquals(Grade.B, p.second);

        p = processScores(85.0)
        assertEquals(Grade.B, p.second);

        p = processScores(60.0)
        assertEquals(Grade.C, p.second);
    }

    fun updateScore(score: Double): Pair<String, Grade> {
        return when {
            score >= 90.0 -> Pair("Excellent", Grade.A)
            score in 70.0..89.9 -> Pair("Good", Grade.B)
            else -> {
                Pair("Acceptable", Grade.C)
            }
        }

//        println("here we are")
//
//        //Use the pair
//        //other code
//
//        return pair
    }

    fun updateScore2(score: Double): Pair<String, Grade> =
            when {
                score >= 90.0 -> Pair("Excellent", Grade.A)
                score in 70.0..89.9 -> Pair("Good", Grade.B)
                else -> {
                    Pair("Acceptable", Grade.C)
                }
            }

    @Test
    fun testUpdateScores() {
        var p = updateScore(91.0);
        assertEquals(Grade.A, p.second);

        //destructuring
        val (description, grade) = updateScore(91.0)
        assertEquals("Excellent", description)
        assertEquals(Grade.A, grade)

        p = updateScore(89.9)
        assertEquals(Grade.B, p.second);

        p = updateScore(85.0)
        assertEquals(Grade.B, p.second);

        p = updateScore(60.0)
        assertEquals(Grade.C, p.second);
    }


    //Using an Enum in a if/vs when.

    //With an if, you have to provide an 'else'
    //clause, or the code below won't compile.
    //Also shows the use of 'if' as an expression
    fun aVarWithIf(grade: Grade): String {
        val result = if (grade == Grade.A) {
            "Excellent"
        } else if (grade == Grade.B) {
            "Good"
        } else if (grade == Grade.C) {
            "Acceptable"
        }
        else {
            throw UnsupportedOperationException("Unknown Grade: $grade")
        }
        return result
    }

    @Test
    fun aVarWithIf() {
        var p = aVarWithIf(Grade.A)
        assertEquals("Excellent", p);

        p = aVarWithIf(Grade.B)
        assertEquals("Good", p);

        p = aVarWithIf(Grade.C)
        assertEquals("Acceptable", p);
    }

    enum class Grade {
        A, B, C
    }

    //No 'else' required if you use a 'when'
    fun aVarWithWhen(grade: Grade): String {
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
        Grade.A, Grade.B -> "Excellent"
        Grade.B -> "Good"
        Grade.C -> "Acceptable"
    }

    @Test
    fun aVarWithIfCleaned() {
        var p = aVarWithIf(Grade.A)
        assertEquals("Excellent", p);

        p = aVarWithIf(Grade.B)
        assertEquals("Good", p);

        p = aVarWithIf(Grade.C)
        assertEquals("Acceptable", p);
    }

    fun smartCastingWithWhen(obj: Any) =
       when(obj) {
           is String -> obj.length
           is Int -> obj
           is Number -> Math.ceil(obj.toDouble())
           is MyClass -> obj.prop
           else -> 0
       }

    class MyClass {
        val prop = 10
    }

    @Test
    fun testSmartCastingWithWhen() {
        val r1 = smartCastingWithWhen(22)
        assertEquals(22, r1)

        val r2 = smartCastingWithWhen("abcde")
        assertEquals(5, r2)

        val r3 = smartCastingWithWhen(22.7)
        assertEquals(23.0, r3)
    }
}