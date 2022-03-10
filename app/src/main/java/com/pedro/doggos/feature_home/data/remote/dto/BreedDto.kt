package com.pedro.doggos.feature_home.data.remote.dto

import com.pedro.doggos.feature_home.domain.model.Breed

data class BreedDto(
    val id: String,
    val name: String,
) {
    fun toBreed(): Breed {
        return Breed(
            id = id,
            name = name
        )
    }
}