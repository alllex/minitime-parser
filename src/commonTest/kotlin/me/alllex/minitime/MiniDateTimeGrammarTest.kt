package me.alllex.minitime

import kotlin.test.Test
import kotlin.test.assertEquals
import me.alllex.parsus.parser.*

class MiniDateTimeGrammarTest {

    @Test
    fun test1() {
        checkParsing(
            listOf("12:34"),
            MiniDateTime(MiniTime(12, 34))
        )

        checkParsing(
            listOf("05:9"),
            MiniDateTime(MiniTime(5, 9))
        )
    }

    @Test
    fun test2() {
        checkParsing(
            listOf("3pm", "3 pm", "3:00pm", "3:00 pm"),
            MiniDateTime(MiniTime(15, 0))
        )
    }

    @Test
    fun test3() {
        checkParsing(
            listOf("2023-08-05", "2023-8-5", "2023/08/05", "2023 08 05", "2023.08.05", "2023 08-05"),
            MiniDateTime(MiniDate(2023, 8, 5))
        )
    }

    @Test
    fun test4() {
        checkParsing(
            listOf("2077-11-30 05:25", "2077-11-30 5:25 am"),
            MiniDateTime(MiniDate(2077, 11, 30), MiniTime(5, 25))
        )
    }

    @Test
    fun test5() {
        checkParsing(
            listOf("10/12/2023"),
            MiniDateTime(MiniDate(2023, 12, 10))
        )

        checkParsing(
            listOf("10/12/2023"),
            MiniDateTime(MiniDate(2023, 10, 12))
        ) {
            MiniDateTimeGrammar(dayThenMonth = false)
        }
    }

    private fun checkParsing(
        inputs: List<String>,
        expected: MiniDateTime,
        create: () -> MiniDateTimeGrammar = { MiniDateTimeGrammar() }
    ) {
        for (input in inputs) {
            val actual = create().parse(input)
            assertEquals(ParsedValue(expected), actual, "input: $input")
        }
    }

}
