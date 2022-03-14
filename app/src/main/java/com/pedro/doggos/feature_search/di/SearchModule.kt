package com.pedro.doggos.feature_search.di

import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.core.data.remote.di.NetworkModule
import com.pedro.doggos.feature_search.data.repository.BreedsRepositoryImpl
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun providesGetBreedsSearchUseCase(repository: BreedsRepository): GetBreedsSearchUseCase {
        return GetBreedsSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesBreedsRepository(remote: ApiManager): BreedsRepository {
        return BreedsRepositoryImpl(remote = remote)
    }
}