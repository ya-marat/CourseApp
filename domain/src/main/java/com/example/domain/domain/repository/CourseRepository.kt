package com.example.domain.domain.repository

import com.example.domain.domain.models.Course
import com.example.domain.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    val favouriteCourses: Flow<List<Course>>

    suspend fun addCourseToFavourite(course: Course): Result<Unit>

    suspend fun removeCourseFromFavourite(courseId: Int): Result<Unit>

    suspend fun loadCourses(): Result<List<Course>>
}