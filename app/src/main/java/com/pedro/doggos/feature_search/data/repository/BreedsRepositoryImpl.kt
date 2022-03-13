package com.pedro.doggos.feature_search.data.repository

import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import io.reactivex.Single

class BreedsRepositoryImpl (private val remote: ApiManager): BreedsRepository {

    override fun getBreedsSearch(searchQuery: String): Single<List<Breed>> {
        return remote.getBreedsSearchService()
            .getSearchBreeds(searchQuery)
            .map { list ->
                list.map { it.toBreed() }
            }
    }
}