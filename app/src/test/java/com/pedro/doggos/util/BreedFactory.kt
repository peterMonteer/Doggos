package com.pedro.doggos.util

import com.pedro.doggos.core.data.remote.dto.BreedDto

object BreedFactory {

    fun createBreedDto(): BreedDto {
        return BreedDto(
            name = randomString(),
            origin = randomString(),
            group = randomString(),
            temperament = randomString()
        )
    }
}