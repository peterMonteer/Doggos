package com.pedro.doggos.feature_home.data.remote.service

import com.pedro.doggos.feature_home.data.remote.dto.BreedImageDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedImagesService {

    @GET("images/search")
    fun getBreedImages(): Single<List<BreedImageDto>>

}