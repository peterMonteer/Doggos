package com.pedro.doggos.feature_home.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedro.doggos.feature_home.data.local.entity.BreedEntity

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreedEntity(breeds: List<BreedEntity>)

    @Query("DELETE FROM breed WHERE id IN(:ids)")
    fun deleteBreeds(ids: List<String>)

    @Query("SELECT * FROM breed")
    fun getBreeds(): PagingSource<Int,BreedEntity>
}