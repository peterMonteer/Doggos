package com.pedro.doggos

import com.pedro.doggos.core.data.remote.dto.BreedDto
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_home.data.remote.dto.BreedImageDto
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.pedro.doggos.util.BreedFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ModelsTest {

    @Test
    fun mapBreedDtoToBreed() {
        val breedDto = BreedFactory.createBreedDto()
        val breed = breedDto.toBreed()
        assertBreedDataEquality(breedDto, breed)
    }

    @Test
    fun mapBreedImageDtoToBreedImage() {
        val breedImageDto = BreedFactory.createBreedImageDto()
        val breedImage = breedImageDto.toBreedImage()
        assertBreedImageDataEquality(breedImageDto, breedImage)
    }

    private fun assertBreedDataEquality(breedDto: BreedDto, breed: Breed) {
        assertEquals(breedDto.name, breed.name)
        assertEquals(breedDto.origin, breed.origin)
        assertEquals(breedDto.temperament, breed.temperament)
        assertEquals(breedDto.group, breed.group)
    }

    private fun assertBreedImageDataEquality(breedImageDto: BreedImageDto, breedImage: BreedImage) {
        assertEquals(breedImageDto.url, breedImage.url)
        assertEquals(breedImageDto.breeds.first().toBreed(), breedImage.breed)
    }
}