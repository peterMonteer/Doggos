package com.pedro.doggos.core.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class HeaderInterceptor

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {

    /**
     * Provide a [HeaderInterceptor] to inject in the OkHttpClient.
     */
    @Provides
    @Singleton
    @HeaderInterceptor
    fun providesHeaderInterceptor() : Interceptor {
        return com.pedro.doggos.core.data.remote.interceptors.HeaderInterceptor()
    }

    /**
     * Provide a [HttpLoggingInterceptor] to inject in the OkHttpClient.
     */
    @Provides
    @Singleton
    @LoggingInterceptor
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}