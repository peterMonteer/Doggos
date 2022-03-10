package com.pedro.doggos.core.data.remote

import com.pedro.doggos.feature_home.data.remote.service.BreedImagesService
import retrofit2.Retrofit
import javax.inject.Inject

class ApiManager @Inject constructor(private val retrofitAdapter: Retrofit) {

    fun getBreedImageService(): BreedImagesService {
        return retrofitAdapter.create(BreedImagesService::class.java)
    }

}