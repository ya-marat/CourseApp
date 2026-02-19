package com.example.feature_course

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateString(pattern: String = "yyyy-MM-dd"): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.format(Date(this))
}

fun String.toMillis(pattern: String = "yyyy-MM-dd"): Long {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.parse(this)?.time ?: 0L
}