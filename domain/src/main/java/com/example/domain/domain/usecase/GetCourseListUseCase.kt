package com.example.domain.domain.usecase

import com.example.domain.domain.repository.RemoteCourseRepository
import javax.inject.Inject

class GetCourseListUseCase @Inject constructor(
    val repository: RemoteCourseRepository
) {
    operator fun invoke() = repository.getCourses()
}