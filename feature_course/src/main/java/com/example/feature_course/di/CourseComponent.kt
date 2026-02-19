package com.example.feature_course.di

import com.example.core.di.ViewModelFactory
import com.example.feature_course.mainscreen.CourseViewModel
import dagger.Component

@Component(
    dependencies = [CourseDependencies::class],
    modules = [CourseModule::class]
)
interface CourseComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface CourseComponentFactory {
        fun create(deps: CourseDependencies): CourseComponent
    }
}