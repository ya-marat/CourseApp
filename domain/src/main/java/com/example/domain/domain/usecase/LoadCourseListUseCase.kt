package com.example.domain.domain.usecase

import com.example.domain.domain.repository.CourseRepository
import javax.inject.Inject

class LoadCourseListUseCase @Inject constructor(
    val repository: CourseRepository
) {
    operator fun invoke() = repository.loadCourses()
}