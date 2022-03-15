package com.pedro.doggos.feature_search.domain.repository

import com.pedro.doggos.core.domain.model.Breed
import io.reactivex.Single

interface BreedsRepository {
    fun getBreedsSearchFromRemote(searchQuery: String): Single<List<Breed>>
    fun getBreedsSearchFromLocalStorage(searchQuery: String): Single<List<Breed>>
}