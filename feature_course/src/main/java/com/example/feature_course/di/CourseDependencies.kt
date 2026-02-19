package com.example.feature_course.di

import com.example.domain.domain.usecase.ObserveFavouriteCoursesUseCase
import com.example.domain.domain.usecase.LoadCourseListUseCase
import com.example.domain.domain.usecase.ObserveCoursesUseCase
import com.example.domain.domain.usecase.ToggleFavouriteCourseUseCase

interface CourseDependencies {

    fun getCourseUseCase(): LoadCourseListUseCase
    fun getFavouritesUseCase(): ObserveFavouriteCoursesUseCase
    fun getObserveCourseUseCase(): ObserveCoursesUseCase
    fun getToggleFavouriteCourseUseCase(): ToggleFavouriteCourseUseCase
}