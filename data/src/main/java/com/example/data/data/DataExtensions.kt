package com.example.data.data

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toMillis(pattern: String = "yyyy-MM-dd"): Long {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.parse(this)?.time ?: 0L
}