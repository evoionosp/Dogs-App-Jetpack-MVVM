package com.evoionosp.dogs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DogImageListResponse(
    val message: List<String>,
    val status: String
)