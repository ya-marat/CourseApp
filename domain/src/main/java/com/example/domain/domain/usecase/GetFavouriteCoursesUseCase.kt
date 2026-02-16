package com.example.domain.domain.usecase

import com.example.domain.domain.repository.FavouriteCourseRepository
import javax.inject.Inject

class GetFavouriteCoursesUseCase @Inject constructor(
    val repository: FavouriteCourseRepository
) {
    operator fun invoke() = repository.favouriteCourses
}