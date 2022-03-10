package com.pedro.doggos.core.data.remote.di

import com.pedro.doggos.core.data.remote.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ApiManagerModule {

    @Provides
    @Singleton
    fun providesApiManager(retrofit: Retrofit): ApiManager {
        return ApiManager(retrofit)
    }
}