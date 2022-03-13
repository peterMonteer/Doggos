package com.pedro.doggos.feature_search.di

import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.core.data.remote.di.NetworkModule
import com.pedro.doggos.feature_home.data.repository.BreedImagesRepositoryImpl
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import com.pedro.doggos.feature_home.domain.use_case.GetBreedImages
import com.pedro.doggos.feature_search.data.repository.BreedsRepositoryImpl
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import com.pedro.doggos.feature_search.domain.use_case.GetBreedsSearch
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
    fun providesGetBreedsSearchUseCase(repository: BreedsRepository): GetBreedsSearch {
        return GetBreedsSearch(repository)
    }

    @Provides
    @Singleton
    fun providesBreedsRepository(remote: ApiManager): BreedsRepository {
        return BreedsRepositoryImpl(remote = remote)
    }
}