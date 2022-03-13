package com.pedro.doggos.core.data.remote

import com.pedro.doggos.feature_home.data.remote.service.BreedImagesService
import com.pedro.doggos.feature_search.data.remote.service.BreedsService
import retrofit2.Retrofit
import javax.inject.Inject

class ApiManager @Inject constructor(private val retrofitAdapter: Retrofit) {

    fun getBreedImageService(): BreedImagesService {
        return retrofitAdapter.create(BreedImagesService::class.java)
    }

    fun getBreedsSearchService(): BreedsService {
        return retrofitAdapter.create(BreedsService::class.java)
    }

}