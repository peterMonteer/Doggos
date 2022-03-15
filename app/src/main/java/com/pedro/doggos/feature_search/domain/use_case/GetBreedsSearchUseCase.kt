package com.pedro.doggos.feature_search.domain.use_case

import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import io.reactivex.Single

class GetBreedsSearchUseCase (private val repository: BreedsRepository) {

    operator fun invoke(searchQuery: String): Single<List<Breed>> {
        return repository.getBreedsSearchFromRemote(searchQuery)
    }

    fun getBreedsSearchFromLocalStorage(searchQuery: String): Single<List<Breed>> {
        return repository.getBreedsSearchFromLocalStorage(searchQuery)
    }
}