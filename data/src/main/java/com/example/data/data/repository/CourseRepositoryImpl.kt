package com.example.data.data.repository

import com.example.domain.domain.models.Course
import com.example.domain.domain.repository.CourseRepository
import com.example.domain.domain.result.Result
import kotlinx.coroutines.flow.Flow

class CourseRepositoryImpl: CourseRepository {

    override val favouriteCourses: Flow<List<Course>>
        get() = TODO("Not yet implemented")

    override fun addCourseToFavourite(course: Course): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun removeCourseFromFavourite(courseId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override fun loadCourses(): Result<List<Course>> {
        TODO("Not yet implemented")
    }
}