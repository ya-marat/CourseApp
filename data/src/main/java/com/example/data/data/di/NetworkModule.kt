package com.example.data.data.di

import com.example.data.data.remote.network.ApiFactory
import com.example.data.data.remote.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}