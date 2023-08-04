@file:OptIn(ExperimentalJsExport::class)

package me.alllex.minitime

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
data class MiniDateTime(val date: MiniDate? = null, val time: MiniTime? = null)

@JsExport
data class MiniDate(val year: Int, val month: Int = 1, val dayOfMonth: Int = 1)

@JsExport
data class MiniTime(val hour: Int, val minute: Int = 0)

