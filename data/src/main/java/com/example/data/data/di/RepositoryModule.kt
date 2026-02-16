package com.example.data.data.di

import com.example.data.data.repository.CourseRepositoryImpl
import com.example.domain.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @AppScope
    @Binds
    fun bindCourseRepository(impl: CourseRepositoryImpl): CourseRepository
}