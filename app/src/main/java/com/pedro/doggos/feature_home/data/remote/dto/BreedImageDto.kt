package com.pedro.doggos.feature_home.data.remote.dto

import com.pedro.doggos.core.data.remote.dto.BreedDto
import com.pedro.doggos.feature_home.domain.model.BreedImage

data class BreedImageDto(
    val breeds: List<BreedDto>,
    val id: String,
    val url: String,
) {
    fun toBreedImage(): BreedImage {
        return BreedImage(
            breed = breeds[0].toBreed(),
            id = id,
            url = url
        )
    }
}