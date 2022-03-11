package com.pedro.doggos.feature_home.domain.repository

import com.pedro.doggos.feature_home.domain.model.BreedImage
import io.reactivex.Single

interface BreedImagesRepository {

    fun getBreedImages(order: String): Single<List<BreedImage>>
}