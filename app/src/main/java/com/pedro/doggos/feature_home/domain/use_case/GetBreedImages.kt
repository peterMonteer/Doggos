package com.pedro.doggos.feature_home.domain.use_case

import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import io.reactivex.Single

class GetBreedImages (
    private val repository: BreedImagesRepository
        ) {

    operator fun invoke(): Single<List<BreedImage>> {
        return repository.getBreedImages()
    }
}