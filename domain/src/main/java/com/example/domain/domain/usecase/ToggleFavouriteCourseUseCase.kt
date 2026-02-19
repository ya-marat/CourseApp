package com.example.domain.domain.usecase

import com.example.domain.domain.models.Course
import com.example.domain.domain.repository.CourseRepository
import javax.inject.Inject

class ToggleFavouriteCourseUseCase @Inject constructor(
    val repository: CourseRepository
) {
    suspend operator fun invoke(courseId: Int) = repository.toggleFavouriteCourse(courseId)
}