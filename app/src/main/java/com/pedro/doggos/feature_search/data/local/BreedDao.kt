package com.pedro.doggos.feature_search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedro.doggos.feature_search.data.local.entity.BreedEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreeds(breeds: List<BreedEntity>): Completable

    @Query("DELETE FROM breed_search WHERE name IN(:breeds)")
    fun deleteBreeds(breeds: List<String>)

    @Query("SELECT * FROM breed_search WHERE name LIKE '%' || :name || '%'")
    fun getBreeds(name: String): Single<List<BreedEntity>>
}