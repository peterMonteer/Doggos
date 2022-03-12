package com.pedro.doggos.feature_home.domain.repository

import androidx.paging.PagingData
import com.pedro.doggos.feature_home.domain.model.BreedImage
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface BreedImagesRepository {
    fun getBreedImages(order: String): Observable<PagingData<BreedImage>>
}