package com.pedro.doggos.feature_home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pedro.doggos.core.domain.model.Breed

@Entity(tableName = "breed")
data class BreedEntity(
    @ColumnInfo(name = "breed_id") val breedId: Int,
    val name: String,
    @PrimaryKey val id: Int? = null
) {
    /*fun toBreed(): Breed {
        return Breed(
            id = breedId,
            name = name
        )
    }*/
}