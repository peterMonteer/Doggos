package com.pedro.doggos.feature_home.data.remote.dto

import com.pedro.doggos.core.data.remote.dto.BreedDto
import com.pedro.doggos.feature_home.domain.model.BreedImage

data class BreedImageDto(
    val breeds: List<BreedDto>,
    val url: String,
    val id: String
) {
    fun toBreedImage(): BreedImage {
        return BreedImage(
            breed = breeds.first().toBreed(),
            url = url,
            id = id
        )
    }
}