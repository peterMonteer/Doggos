package com.pedro.doggos.feature_search.di

import android.content.Context
import com.pedro.doggos.core.data.local.DoggoDatabase
import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.core.data.remote.di.NetworkModule
import com.pedro.doggos.core.util.AppSchedulerProvider
import com.pedro.doggos.core.util.SchedulerProvider
import com.pedro.doggos.feature_search.data.repository.BreedsRepositoryImpl
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun providesGetBreedsSearchUseCase(repository: BreedsRepository): GetBreedsSearchUseCase {
        return GetBreedsSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesBreedsRepository(remote: ApiManager, @ApplicationContext context: Context): BreedsRepository {
        return BreedsRepositoryImpl(remote = remote, dao = DoggoDatabase.getInstance(context).breedDao)
    }

    @Provides
    @Singleton
    fun providesScheduler() : SchedulerProvider {
        return AppSchedulerProvider()
    }
}