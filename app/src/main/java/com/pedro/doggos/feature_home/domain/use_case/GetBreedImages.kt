package com.pedro.doggos.feature_home.domain.use_case

import androidx.paging.PagingData
import androidx.paging.filter
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import io.reactivex.Flowable
import io.reactivex.Observable

class GetBreedImages (private val repository: BreedImagesRepository) {

    operator fun invoke(order: String = "RANDOM"): Observable<PagingData<BreedImage>> {
        return repository.getBreedImages(order)
            .map { data ->
                data.filter { it.breeds.isNotEmpty() }
            }
    }
}