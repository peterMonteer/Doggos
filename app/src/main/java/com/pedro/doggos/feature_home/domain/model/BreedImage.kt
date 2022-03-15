package com.pedro.doggos.feature_home.domain.model

import com.pedro.doggos.core.domain.model.Breed

data class BreedImage(
    val id: String,
    val breed: Breed,
    val url: String,
)