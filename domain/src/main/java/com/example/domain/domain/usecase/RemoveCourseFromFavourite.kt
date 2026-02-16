package com.example.domain.domain.usecase

import com.example.domain.domain.repository.CourseRepository
import javax.inject.Inject

class RemoveCourseFromFavourite @Inject constructor(
    val repository: CourseRepository
) {
    operator fun invoke(courseId: Int) = repository.removeCourseFromFavourite(courseId)
}