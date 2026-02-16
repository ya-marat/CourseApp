package com.example.domain.domain.usecase

import com.example.domain.domain.models.Course
import com.example.domain.domain.repository.FavouriteCourseRepository
import javax.inject.Inject

class AddCourseToFavourites @Inject constructor(
    val repository: FavouriteCourseRepository
) {
    operator fun invoke(course: Course) = repository.addCourseToFavourite(course = course)
}