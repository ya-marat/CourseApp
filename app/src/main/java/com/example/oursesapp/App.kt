package com.example.oursesapp

import android.app.Application
import com.example.feature_auth.AuthDependencies
import com.example.feature_auth.AuthDependenciesProvider
import com.example.feature_course.di.CourseDependencies
import com.example.feature_course.di.CourseDependenciesProvider

import kotlin.getValue

class App: Application(), AuthDependenciesProvider, CourseDependenciesProvider {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun provideAuthDependencies(): AuthDependencies {
        return component
    }

    override fun provideCourseDependencies(): CourseDependencies {
        return component
    }
}