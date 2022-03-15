package com.pedro.doggos.feature_search.data.repository

import android.util.Log
import com.pedro.doggos.core.data.remote.ApiManager
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_search.data.local.BreedDao
import com.pedro.doggos.feature_search.domain.repository.BreedsRepository
import io.reactivex.Single

class BreedsRepositoryImpl (private val remote: ApiManager, private val dao: BreedDao): BreedsRepository {

    override fun getBreedsSearchFromRemote(searchQuery: String): Single<List<Breed>> {
        return remote.getBreedsSearchService()
            .getSearchBreeds(searchQuery)
            .map { list -> list.map { it.toBreedEntity() } }
            .flatMap {
                dao.deleteBreeds(it.map { it.name })
                dao.insertBreeds(it).subscribe()
                dao.getBreeds(searchQuery).map { list -> list.map { it.toBreed() } }
            }
    }

    override fun getBreedsSearchFromLocalStorage(searchQuery: String): Single<List<Breed>> {
        return dao.getBreeds(searchQuery)
            .map { list -> list.map { it.toBreed() } }
    }
}