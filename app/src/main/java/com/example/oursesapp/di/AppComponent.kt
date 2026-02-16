package com.example.oursesapp.di

import android.app.Application
import com.example.data.data.di.AppScope
import com.example.data.data.di.DbModule
import com.example.data.data.di.NetworkModule
import com.example.data.data.di.RepositoryModule
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
interface AppComponent {

    //fun inject()

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}