package com.example.feature_auth

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ViewModelFactory
import dagger.Component

@Component(
    dependencies = [AuthDependencies::class],
    modules = [AuthViewModelModule::class]
)
interface AuthComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Factory
    interface AuthComponentFactory {

        fun create(deps: AuthDependencies): AuthComponent
    }
}