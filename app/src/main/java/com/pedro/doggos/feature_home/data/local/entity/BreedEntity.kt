package com.pedro.doggos.feature_home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pedro.doggos.core.domain.model.Breed

@Entity(tableName = "breed")
data class BreedEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val group: String?,
    val origin: String?,
    val temperament: String?
) {
    fun toBreed(): Breed {
        return Breed(
            name = name,
            group = group,
            origin = origin,
            temperament = temperament
        )
    }
}