package com.pedro.doggos.core.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_search.data.local.entity.BreedEntity

data class BreedDto(
    @SerializedName("breed_group")
    val group: String,
    val name: String,
    val origin: String?,
    val temperament: String?
) {
    fun toBreed(): Breed {
        return Breed(
            name = name,
            origin = origin,
            group = group,
            temperament = temperament
        )
    }
    fun toBreedEntity(): BreedEntity {
        return BreedEntity(
            name = name,
            group = group,
            origin = origin,
            temperament = temperament
        )
    }
}