package com.example.feature_course.di

import com.example.domain.domain.usecase.LoadCourseListUseCase

interface CourseDependencies {

    fun getCourseUseCase(): LoadCourseListUseCase
}