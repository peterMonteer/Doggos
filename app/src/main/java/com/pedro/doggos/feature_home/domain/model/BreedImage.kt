package com.pedro.doggos.feature_home.domain.model

data class BreedImage(
    val breeds: List<Breed>,
    val id: String,
    val url: String,
)