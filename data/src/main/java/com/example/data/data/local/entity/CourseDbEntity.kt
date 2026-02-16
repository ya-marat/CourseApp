package com.example.data.data.local.entity

import androidx.room.Entity

@Entity(tableName = "courses")
data class CourseDbEntity (
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val hasLike: Boolean,
    val publishDate: String
)