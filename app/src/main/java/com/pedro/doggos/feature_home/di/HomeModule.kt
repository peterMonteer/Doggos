package com.pedro.doggos.feature_home.di

import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.core.data.remote.di.NetworkModule
import com.pedro.doggos.feature_home.data.repository.BreedImagesRepositoryImpl
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun providesGetBreedImageUseCase(repository: BreedImagesRepository): GetBreedImagesUseCase {
        return GetBreedImagesUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesBreedImagesRepository(remote: ApiManager): BreedImagesRepository {
        return BreedImagesRepositoryImpl(remote = remote)
    }
}