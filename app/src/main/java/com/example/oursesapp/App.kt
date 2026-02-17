package com.example.oursesapp

import android.app.Application
import com.example.feature_auth.AuthDependencies
import com.example.feature_auth.AuthDependenciesProvider

import kotlin.getValue

class App: Application(), AuthDependenciesProvider {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun provideAuthDependencies(): AuthDependencies {
        return component
    }
}