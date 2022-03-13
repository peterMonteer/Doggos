package com.pedro.doggos.feature_search.domain.use_case

import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import io.reactivex.Single

class GetBreedsSearch (private val repository: BreedsRepository) {

    operator fun invoke(searchQuery: String): Single<List<Breed>> {
        return repository.getBreedsSearch(searchQuery)
    }
}