package com.pedro.doggos.feature_home.data.repository

import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import io.reactivex.Single

class BreedImagesRepositoryImpl (private val remote: ApiManager): BreedImagesRepository {
    override fun getBreedImages(order: String): Single<List<BreedImage>> {
        return remote.getBreedImageService()
            .getBreedImages(order)
            .map { list ->
                list
                    .filter { it.breeds.isNotEmpty() }
                    .map { it.toBreedImage() }
        }
    }
}