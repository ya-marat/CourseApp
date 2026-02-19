package com.example.feature_course.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.feature_course.favourite.FavouriteCourseViewModel
import com.example.feature_course.mainscreen.CourseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CourseModule {

    @Binds
    @IntoMap
    @ViewModelKey(CourseViewModel::class)
    fun bindCourseViewModel(viewModel: CourseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteCourseViewModel::class)
    fun bindFavouriteCourseViewModel(viewModel: FavouriteCourseViewModel): ViewModel
}