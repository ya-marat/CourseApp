package com.example.domain.domain.usecase

import com.example.domain.domain.models.Course
import com.example.domain.domain.repository.CourseRepository
import javax.inject.Inject

class AddCourseToFavourite @Inject constructor(
    val repository: CourseRepository
) {
    suspend operator fun invoke(course: Course) = repository.addCourseToFavourite(course = course)
}