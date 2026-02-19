package com.example.data.data.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Utils {

    fun parseDateStringToCalendar(date: String): Calendar {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = format.parse(date)

        val calendar = Calendar.getInstance().apply {
            time = parsedDate ?: Date(0)
        }

        return calendar
    }

    fun parseToMillis(date: String): Long {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(date)?.time ?: 0L
    }
}