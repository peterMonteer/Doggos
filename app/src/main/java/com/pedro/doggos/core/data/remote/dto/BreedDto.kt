package com.pedro.doggos.core.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.pedro.doggos.core.domain.model.Breed

data class BreedDto(
    @SerializedName("breed_group")
    val breedGroup: String,
    val id: Int,
    val name: String,
    val origin: String?,
    val temperament: String?
) {
    fun toBreed(): Breed {
        return Breed(
            id = id,
            name = name,
            origin = origin,
            breedGroup = breedGroup,
            temperament = temperament
        )
    }
}