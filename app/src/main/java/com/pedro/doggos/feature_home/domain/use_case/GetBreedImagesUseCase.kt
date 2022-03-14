package com.pedro.doggos.feature_home.domain.use_case

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope

class GetBreedImagesUseCase (private val repository: BreedImagesRepository) {

    operator fun invoke(order: String, scope: CoroutineScope): Observable<PagingData<BreedImage>> {
        return repository.getBreedImages(order)
            .cachedIn(scope)
    }
}