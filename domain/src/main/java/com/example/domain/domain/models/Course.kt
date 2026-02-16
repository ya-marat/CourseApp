package com.example.domain.domain.models

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val hasLike: Boolean,
    val publishDate: String
)