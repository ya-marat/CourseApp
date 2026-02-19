package com.example.oursesapp

import android.app.Application
import com.example.data.data.di.AppScope
import com.example.data.data.di.DbModule
import com.example.data.data.di.NetworkModule
import com.example.data.data.di.RepositoryModule
import com.example.domain.domain.repository.CourseRepository
import com.example.feature_auth.AuthDependencies
import com.example.feature_course.di.CourseDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        RepositoryModule::class,
        DbModule::class,
        NetworkModule::class
    ]
)
interface AppComponent: AuthDependencies, CourseDependencies {

    //fun inject()

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}