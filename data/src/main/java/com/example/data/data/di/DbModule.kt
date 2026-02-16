package com.example.data.data.di

import android.app.Application
import com.example.data.data.local.db.AppDatabase
import com.example.data.data.local.db.DatabaseDao
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @AppScope
    @Provides
    fun provideDatabaseDao(application: Application): DatabaseDao {
        return AppDatabase.getInstance(application).appDatabaseDao()
    }
}