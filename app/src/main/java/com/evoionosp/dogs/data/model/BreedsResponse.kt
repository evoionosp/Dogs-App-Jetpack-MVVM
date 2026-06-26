package com.evoionosp.dogs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)