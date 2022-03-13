package com.pedro.doggos.feature_home.domain.model

import com.pedro.doggos.core.domain.model.Breed

data class BreedImage(
    val breeds: List<Breed>,
    val id: String,
    val url: String,
)