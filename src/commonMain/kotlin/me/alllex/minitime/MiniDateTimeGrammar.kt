package me.alllex.minitime

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.*


@Suppress("MemberVisibilityCanBePrivate")
class MiniDateTimeGrammar(
    private val dayThenMonth: Boolean = true,
) : Grammar<MiniDateTime>(ignoreCase = true) {

    // important it goes before digit12
    val digit4 by regexToken("\\d{4}") map { it.text.toInt() }
    val digit12 by regexToken("\\d{1,2}") map { it.text.toInt() }
    val am by regexToken("am")
    val pm by regexToken("pm")

    val colon by literalToken(":")
    val dash by literalToken("-")
    val slash by literalToken("/")
    val dot by literalToken(".")
    val comma by literalToken(",")

    val ws by regexToken("\\s+")

    val time24: Parser<MiniTime>
        by digit12 * -colon * digit12 map
            { (h: Int, m: Int) -> MiniTime(h, m) }

    val afterNoon: Parser<Int> by am or pm map
        { if (it.token == pm) 12 else 0 }

    val timeAmPm: Parser<MiniTime>
        by digit12 * maybe(-colon * digit12) * -maybe(ws) * afterNoon map
            { (h, m, amPm) -> MiniTime((h + amPm) % 24, m ?: 0) }

    val dateSep by dash or slash or dot or comma or ws

    val yyyyMMdd: Parser<MiniDate>
        by digit4 * -dateSep * digit12 * -dateSep * digit12 map
            { (y: Int, m: Int, d: Int) -> MiniDate(y, m, d) }

    val d2d2yyyy: Parser<MiniDate>
        by digit12 * -dateSep * digit12 * -dateSep * digit4 map
            { (v1: Int, v2: Int, y: Int) ->
                if (dayThenMonth) MiniDate(y, month = v2, v1) else MiniDate(y, month = v1, v2)
            }

    val time: Parser<MiniTime> by timeAmPm or time24

    val date: Parser<MiniDate> by yyyyMMdd or d2d2yyyy

    val dateTime: Parser<MiniDateTime>
        by maybe(date) * -maybe(ws) * maybe(time) map
            { (d: MiniDate?, t: MiniTime?) -> MiniDateTime(d, t) }

    override val root by dateTime
}
