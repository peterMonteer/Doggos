package com.pedro.doggos.feature_home.data.remote.service

import com.pedro.doggos.feature_home.data.remote.dto.BreedImageDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BreedImagesService {

    /* Query Params
        order: String, Allowed Values: RANDOM, ASC, DESC
        limit: int
        page: int
     */

    @GET("images/search")
    fun getBreedImages(@Query("order") order: String = "RANDOM",
                       @Query("limit") limit: Int = 20,
                       @Query("page") page: Int = 0): Single<List<BreedImageDto>>

}