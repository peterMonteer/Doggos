package com.pedro.doggos.feature_home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.feature_home.data.BreedImagesPagingSource
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.feature_home.domain.repository.BreedImagesRepository
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope

class BreedImagesRepositoryImpl (private val remote: ApiManager): BreedImagesRepository {
    override fun getBreedImages(order: String): Observable<PagingData<BreedImage>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BreedImagesPagingSource(remote.getBreedImageService(), order) }
        )

        return pager.observable
    }
}