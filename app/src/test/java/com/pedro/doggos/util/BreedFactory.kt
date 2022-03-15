package com.pedro.doggos.util

import com.pedro.doggos.core.data.remote.dto.BreedDto
import com.pedro.doggos.feature_home.data.remote.dto.BreedImageDto

object BreedFactory {

    fun createBreedDto(): BreedDto {
        return BreedDto(
            name = randomString(),
            origin = randomString(),
            group = randomString(),
            temperament = randomString()
        )
    }

    fun createBreedImageDto(): BreedImageDto {
        return BreedImageDto(
            url = randomString(),
            breeds = listOf(createBreedDto()),
            id = randomString()
        )
    }
}