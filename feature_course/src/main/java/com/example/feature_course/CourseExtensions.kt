package com.example.feature_course

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
    Форсировано устанавливаю русскую локаль чтобы на любом устройстве с любой локалью
    был русский язык. Это в рамках тестового разумеется
 */
fun Long.toDateString(pattern: String = "d MMMM yyyy"): String {
    val format = SimpleDateFormat(pattern, Locale.forLanguageTag("ru-RU"))
    return format.format(Date(this))
}

fun String.toMillis(pattern: String = "yyyy-MM-dd"): Long {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.parse(this)?.time ?: 0L
}