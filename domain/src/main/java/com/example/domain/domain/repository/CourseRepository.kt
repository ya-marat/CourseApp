package com.example.domain.domain.repository

import com.example.domain.domain.models.Course
import com.example.domain.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun observeFavouriteCourses(): Flow<List<Course>>

    fun observeCourses(): Flow<List<Course>>

    suspend fun toggleFavouriteCourse(courseId: Int): Result<Unit>

    suspend fun loadCourses(): Result<Unit>
}