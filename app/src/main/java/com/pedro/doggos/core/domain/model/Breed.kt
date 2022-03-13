package com.pedro.doggos.core.domain.model

data class Breed(
    val id: Int,
    val name: String,
    val origin: String?,
    val breedGroup: String?,
    val temperament: String?
)