package com.evoionosp.dogs.domain.model

data class Breed(
    val name: String,
    val subBreeds: List<String>,
    val imageUrl: String
)