package com.pedro.doggos.feature_search.data.remote.service

import com.pedro.doggos.core.data.remote.dto.BreedDto
import com.pedro.doggos.feature_home.data.remote.dto.BreedImageDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BreedsService {

    /* Query Params
        q: String, Part of the name of the Breed to search for
     */

    @GET("breeds/search")
    fun getSearchBreeds(@Query("q") searchQuery: String): Single<List<BreedDto>>

}