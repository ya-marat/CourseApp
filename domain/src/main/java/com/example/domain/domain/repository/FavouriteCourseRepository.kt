package com.example.domain.domain.repository

import com.example.domain.domain.models.Course
import com.example.domain.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface FavouriteCourseRepository {

    val favouriteCourses: Flow<List<Course>>

    fun addCourseToFavourite(course: Course): Result<Unit>

    fun removeCourseFromFavourite(courseId: Int): Result<Unit>
}