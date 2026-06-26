package com.evoionosp.dogs.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SubBreedsResponse(
    val message: List<String>,
    val status: String
)