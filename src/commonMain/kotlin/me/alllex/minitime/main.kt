@file:OptIn(ExperimentalJsExport::class)

package me.alllex.minitime

import me.alllex.parsus.parser.parseOrNull
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
fun parseDateTime(
    input: String,
    dayThenMonth: Boolean = true,
): MiniDateTime? {
    return MiniDateTimeGrammar(dayThenMonth = dayThenMonth)
        .parseOrNull(input)
}
