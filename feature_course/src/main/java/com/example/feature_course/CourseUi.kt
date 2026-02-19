package com.example.feature_course

data class CourseUi(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val publishDate: String,
    val isFavourite: Boolean,
    val publishDateMillis: Long,
    val coverResId: Int
)